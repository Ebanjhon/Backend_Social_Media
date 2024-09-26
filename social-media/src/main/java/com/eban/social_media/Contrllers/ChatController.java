package com.eban.social_media.Contrllers;

import com.eban.social_media.Models.Chat;
import com.eban.social_media.Models.RoomChat;
import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.ChatServiceImpl;
import com.eban.social_media.Services.ServiceImpl.RoomChatServiceImpl;
import com.eban.social_media.Services.ServiceImpl.UserServiceImpl;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatServiceImpl chatService;

    @Autowired
    private RoomChatServiceImpl roomChatService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/room")
    public ResponseEntity<Long> createRoomChat(@RequestParam Long firstUserId, @RequestParam Long lastUserId){
        Long idRoom = roomChatService.getRoomChat(firstUserId, lastUserId);
        if(idRoom == null){
            RoomChat r = new RoomChat();
            RoomChat room = roomChatService.createRoom(r);

            Chat c1 = new Chat();
            c1.setRoomChat(room);
            User u1 = userService.getUserById(firstUserId);
            c1.setUser(u1);
            chatService.createChat(c1);

            Chat c2 = new Chat();
            c2.setRoomChat(room);
            User u2 = userService.getUserById(lastUserId);
            c2.setUser(u2);
            chatService.createChat(c2);

            return new ResponseEntity<>(room.getId(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(idRoom, HttpStatus.OK);
        }
    }

}
