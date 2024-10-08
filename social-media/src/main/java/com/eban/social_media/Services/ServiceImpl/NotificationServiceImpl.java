package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.DTO.NotifiDTO;
import com.eban.social_media.Models.NotifiType;
import com.eban.social_media.Models.Notification;
import com.eban.social_media.Models.User;
import com.eban.social_media.Repositories.NotificationRepository;
import com.eban.social_media.Services.NotificationService;
import com.eban.social_media.WebSocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private NotificationRepository notificationRepository;

    private final MyWebSocketHandler myWebSocketHandler;

    @Autowired
    public NotificationServiceImpl(MyWebSocketHandler myWebSocketHandler) {
        this.myWebSocketHandler = myWebSocketHandler;
    }

    @Override
    public void createNotification(Long userId, Long userTarget, NotifiType notifiType) throws Exception {
        Notification notifi = new Notification();
        notifi.setNotifiType(notifiType);
        // lấy user tạo thông báo
        User user = userServiceImpl.getUserById(userId);
        // lấy user nhận thông báo
        User utarget = userServiceImpl.getUserById(userTarget);
        notifi.setUser(utarget);
        notifi.setUserNotifi(user);
        notifi.setRead(false);
        notificationRepository.save(notifi);
        sendNotification(utarget.getId(), user, notifiType);
    }

    @Override
    public List<NotifiDTO> getNotifications(Long userId) {
        return notificationRepository.getNotificationByUserId(userId);
    }

    public void sendNotification(Long id,User user, NotifiType notifiType) throws Exception {
        String mess;
        switch (notifiType)
        {
            case FOLLOW:
                mess = user.getFirstname()+" "+user.getLastname()+" Đã theo dỏi bạn";
                myWebSocketHandler.sendMessageToUser(id.toString(), mess);
                break;
            case LIKE:
                mess = user.getFirstname()+" "+user.getLastname()+" Đã thích bài viết của bạn";
                myWebSocketHandler.sendMessageToUser(id.toString(), mess);
                break;
            case COMMENT:
                mess = user.getFirstname()+" "+user.getLastname()+" Đã bình luận về bài viết của bạn bạn";
                myWebSocketHandler.sendMessageToUser(id.toString(), mess);
                break;
            case REPLY_COMMENT:
                mess = user.getFirstname()+" "+user.getLastname()+" Đã trả lời bình luận";
                myWebSocketHandler.sendMessageToUser(id.toString(), mess);
                break;
        }
    }

}
