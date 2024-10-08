package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Người tạo thông báo
    @ManyToOne
    @JoinColumn(name = "user_notifi_id", nullable = false)
    private User userNotifi;

    // Enum để lưu loại thông báo
    @Enumerated(EnumType.STRING) // Lưu dưới dạng chuỗi trong cơ sở dữ liệu
    @Column(nullable = false)
    private NotifiType notifiType;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private boolean read;

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
        this.read = false;
    }
}
