package com.eban.social_media.Configs;

import com.eban.social_media.WebSocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket // Kích hoạt WebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MyWebSocketHandler myWebSocketHandler;

    @Autowired
    public WebSocketConfig(MyWebSocketHandler myWebSocketHandler) {
        this.myWebSocketHandler = myWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Đăng ký WebSocket handler đã được Spring quản lý
        registry.addHandler(myWebSocketHandler, "/ws")
                .setAllowedOrigins("*");
    }
}