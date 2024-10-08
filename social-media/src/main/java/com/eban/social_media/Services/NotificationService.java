package com.eban.social_media.Services;

import com.eban.social_media.DTO.NotifiDTO;
import com.eban.social_media.Models.NotifiType;
import com.eban.social_media.Models.User;

import java.util.List;

public interface NotificationService {
    void createNotification(Long userId, Long userTarget, NotifiType notifiType) throws Exception;
    List<NotifiDTO> getNotifications(Long userId);
}
