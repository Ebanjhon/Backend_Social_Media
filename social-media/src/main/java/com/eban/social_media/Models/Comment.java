package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    // Một bình luận thuộc về một bài viết
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Foreign key to Post
    
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_parent_id") // Khóa ngoại dùng để tham chiếu đến bình luận cha
    private Comment parentComment;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timeComment;
    @PrePersist
    protected void onCreate() {
        this.timeComment = LocalDateTime.now();
    }
}
