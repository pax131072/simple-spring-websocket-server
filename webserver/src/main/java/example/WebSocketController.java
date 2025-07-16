package example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/ws-api")
public class WebSocketController {

    @GetMapping("/list")
    public Map<String, String> listSessions() {
        Map<String, String> sessions = WebSocketManager.all().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().isOpen() ? "OPEN" : "CLOSED"
                ));

        log.info("[listSessions] Active connections count: {}", sessions.size());
        return sessions;
    }

    @PostMapping("/send")
    public String send(@RequestBody Map<String, String> body) throws IOException {
        log.info("[send] Received message send request: {}", body);

        for (Map.Entry<String, String> entry : body.entrySet()) {
            String uuid = entry.getKey();
            String msg = entry.getValue();

            WebSocketSession session = WebSocketManager.get(uuid);
            if (session != null && session.isOpen()) {
                log.info(" → Sending message to [{}]: {}", uuid, msg);
                session.sendMessage(new TextMessage(msg));
            } else {
                log.warn(" → No available or open session for UUID: {}", uuid);
            }
        }
        return "Sent.";
    }

    @PostMapping("/close")
    public String close(@RequestParam String uuid) throws IOException {
        log.info("[close] Received connection close request for UUID: {}", uuid);

        WebSocketSession session = WebSocketManager.get(uuid);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage("Connection will be closed"));
            session.close();
            log.info(" → Closed session for UUID: {}", uuid);
        } else {
            log.warn(" → No available or open session for UUID: {}", uuid);
        }
        return "Closed.";
    }
}
