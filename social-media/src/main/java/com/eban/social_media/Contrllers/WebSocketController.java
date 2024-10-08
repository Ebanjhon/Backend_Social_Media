package com.eban.social_media.Contrllers;

import com.eban.social_media.WebSocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/websocket")
@CrossOrigin(origins = "*")
public class WebSocketController {

    private final MyWebSocketHandler myWebSocketHandler;

    @Autowired
    public WebSocketController(MyWebSocketHandler myWebSocketHandler) {
        this.myWebSocketHandler = myWebSocketHandler;
    }

    @GetMapping("/test")
    public ResponseEntity<String> getWebSocketTestPage() {
        return ResponseEntity.ok("test-websocket");
    }

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody Map<String, String> payload) throws Exception {
        String message = payload.get("message");
        String userId = payload.get("userId"); // Lấy userId từ request body
        if ("3".equals(userId)) { // Chỉ gửi cho userId = 3
            myWebSocketHandler.sendMessageToUser(userId, message);
            return ResponseEntity.ok("Notification sent to user " + userId + ": " + message);
        } else {
            return ResponseEntity.badRequest().body("Notification can only be sent to user with ID 3.");
        }
    }

}
