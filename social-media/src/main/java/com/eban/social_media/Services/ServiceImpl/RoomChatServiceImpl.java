package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.Models.RoomChat;
import com.eban.social_media.Repositories.RoomChatRepository;
import com.eban.social_media.Services.RoomChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomChatServiceImpl implements RoomChatService {

    @Autowired
    private RoomChatRepository roomChatRepository;

    @Override
    public RoomChat createRoom(RoomChat roomChat) {
        return roomChatRepository.save(roomChat);
    }
}
