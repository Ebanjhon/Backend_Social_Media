package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.NotifiDTO;
import com.eban.social_media.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT new com.eban.social_media.DTO.NotifiDTO(n.id, n.userNotifi.id, n.userNotifi.avatar, n.userNotifi.username, n.userNotifi.firstname, n.userNotifi.lastname, n.timestamp, n.notifiType) " +
            "FROM Notification n " +
            "JOIN n.user u " +  // Người nhận thông báo
            "WHERE u.id = :userId " + // Điều kiện để lấy thông báo của user nhận
            "ORDER BY n.timestamp DESC")
    List<NotifiDTO> getNotificationByUserId(@Param("userId") Long userId);

}
