package example;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomWebSocketHandler extends TextWebSocketHandler {

    @SuppressWarnings("null")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uuid = getUuid(session);
        if (uuid != null && !uuid.isEmpty()) {
            WebSocketManager.add(uuid, session);
            log.info("WebSocket connection established: uuid={}", uuid);
        } else {
            log.warn("WebSocket connection rejected due to missing or empty UUID");
            session.close(CloseStatus.BAD_DATA);
        }
    }

    @SuppressWarnings("null")
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String uuid = getUuid(session);
        log.info("Received message from uuid={}: {}", uuid, message.getPayload());
    }

    @SuppressWarnings("null")
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String uuid = getUuid(session);
        if (uuid != null) {
            WebSocketManager.remove(uuid);
            log.info("WebSocket connection closed: uuid={}, status={}", uuid, status);
        }
    }

    private String getUuid(WebSocketSession session) {
        return (String) session.getAttributes().get("uuid");
    }
}
