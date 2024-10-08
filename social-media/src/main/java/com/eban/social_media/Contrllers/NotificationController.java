package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.NotifiDTO;
import com.eban.social_media.Services.ServiceImpl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    @GetMapping
    public ResponseEntity<List<NotifiDTO>> getNotifications(@RequestParam Long userId) {
        try{
            List<NotifiDTO> notifiDTOS = notificationService.getNotifications(userId);
            return ResponseEntity.ok(notifiDTOS);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
