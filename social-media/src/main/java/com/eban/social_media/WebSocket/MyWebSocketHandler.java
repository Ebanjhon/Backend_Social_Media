package com.eban.social_media.WebSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    // Lưu trữ các phiên WebSocket của các user theo userId
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Giả sử userId được truyền trong query parameter khi kết nối WebSocket
        String userId = getUserIdFromSession(session);
        if (userId != null) {
            sessions.put(userId, session);
            System.out.println("User " + userId + " connected with session ID: " + session.getId());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = getUserIdFromSession(session);
        if (userId != null) {
            sessions.remove(userId);
            System.out.println("User " + userId + " disconnected");
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Xử lý tin nhắn nếu cần
        //Khi một client kết nối tới server WebSocket và gửi một tin nhắn văn bản (dạng TextMessage), phương thức
        //này sẽ được gọi tự động để xử lý nội dung của tin nhắn đó.
    }

    // Gửi tin nhắn đến một user cụ thể
    public void sendMessageToUser(String userId, String message) throws Exception {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
            System.out.println("Message sent to user " + userId);
        } else {
            System.out.println("No active session found for user " + userId);
        }
    }

    // Helper method để lấy userId từ WebSocketSession (ví dụ từ query params)
    private String getUserIdFromSession(WebSocketSession session) {
        // Ví dụ userId được truyền trong query parameter
        String query = session.getUri().getQuery();
        if (query != null && query.contains("userId=")) {
            return query.split("=")[1]; // Lấy userId từ query params
        }
        return null;
    }
}