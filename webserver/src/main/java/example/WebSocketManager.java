package example;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketManager {
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public static void add(String uuid, WebSocketSession session) {
        sessions.put(uuid, session);
    }

    public static void remove(String uuid) {
        sessions.remove(uuid);
    }

    public static WebSocketSession get(String uuid) {
        return sessions.get(uuid);
    }

    public static Map<String, WebSocketSession> all() {
        return sessions;
    }
}
