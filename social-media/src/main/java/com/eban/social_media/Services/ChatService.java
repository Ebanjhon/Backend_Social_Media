package com.eban.social_media.Services;

import com.eban.social_media.Models.Chat;

public interface ChatService {
    void createChat(Chat chat);
    void deleteChat(Chat chat);
    Chat getChatById(int id);
}
