package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.DTO.UserFollowChatDTO;
import com.eban.social_media.Models.Chat;
import com.eban.social_media.Repositories.ChatRepository;
import com.eban.social_media.Services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public void createChat(Chat chat) {
        chatRepository.save(chat);
    }

    @Override
    public void deleteChat(Chat chat) {
        chatRepository.delete(chat);
    }

    @Override
    public Chat getChatById(int id) {
        return null;
    }

    @Override
    public List<UserFollowChatDTO> getListChatFollow(Long userId) {
        return chatRepository.getUsersChatByFollowing(userId);
    }
}
