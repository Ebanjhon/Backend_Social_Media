package com.eban.social_media.Services;

import com.eban.social_media.Models.RoomChat;

public interface RoomChatService {
    RoomChat createRoom(RoomChat roomChat);
    Long getRoomChat(Long firstUser, Long lastUser);
}
