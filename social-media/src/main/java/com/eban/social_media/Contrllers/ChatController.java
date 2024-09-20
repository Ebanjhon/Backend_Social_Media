package com.eban.social_media.Contrllers;

import com.eban.social_media.Models.Chat;
import com.eban.social_media.Models.RoomChat;
import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.ChatServiceImpl;
import com.eban.social_media.Services.ServiceImpl.RoomChatServiceImpl;
import com.eban.social_media.Services.ServiceImpl.UserServiceImpl;
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

    @PostMapping
    public ResponseEntity<?> createRoomChat(@PathVariable List<Long> userId){

        RoomChat r = new RoomChat();
        RoomChat IdRoom = roomChatService.createRoom(r);
        for(Long uId: userId){
            Chat c = new Chat();
            c.setRoomChat(r);
            User u = userService.getUserById(uId);
            c.setUser(u);
            chatService.createChat(c);
        }
        return new ResponseEntity<>(r.getId(), HttpStatus.CREATED);
    }
}
