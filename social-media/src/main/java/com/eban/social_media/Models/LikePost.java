package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "likes_post")
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự động sinh

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Khóa ngoại tham chiếu đến bảng User

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Khóa ngoại tham chiếu đến bảng Post
}
