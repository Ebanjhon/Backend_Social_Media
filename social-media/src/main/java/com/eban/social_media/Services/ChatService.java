package com.eban.social_media.Services;

import com.eban.social_media.DTO.UserFollowChatDTO;
import com.eban.social_media.Models.Chat;

import java.util.List;

public interface ChatService {
    void createChat(Chat chat);
    void deleteChat(Chat chat);
    Chat getChatById(int id);
    List<UserFollowChatDTO> getListChatFollow(Long userId);
}
