package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.Models.RoomChat;
import com.eban.social_media.Repositories.RoomChatRepository;
import com.eban.social_media.Services.RoomChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomChatServiceImpl implements RoomChatService {

    @Autowired
    private RoomChatRepository roomChatRepository;

    @Override
    public RoomChat createRoom(RoomChat roomChat) {
        return roomChatRepository.save(roomChat);
    }

    @Override
    public Long getRoomChat(Long firstUser, Long lastUser) {
        Optional<Long> roomChatId = roomChatRepository.getRoomChatByTwoUser(firstUser,lastUser);
        return roomChatId.orElse(null);
    }
}
