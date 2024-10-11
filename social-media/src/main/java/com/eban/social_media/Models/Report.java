package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReport; // Tự động ánh xạ tới cột id trong bảng

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Khóa ngoại tham chiếu đến bảng User

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Khóa ngoại tham chiếu đến bảng Post

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contentReport; // Nội dung báo cáo

    @Column(nullable = false, updatable = false)
    private LocalDateTime timeCreated; // Thời gian tạo báo cáo

    @PrePersist
    protected void onCreate() {
        this.timeCreated = LocalDateTime.now();
    }
}