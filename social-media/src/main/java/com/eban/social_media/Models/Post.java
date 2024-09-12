package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost; // Tự động ánh xạ tới cột id trong bảng

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Khóa ngoại tham chiếu đến bảng User

    @Column(nullable = false, updatable = false)
    private LocalDateTime postDate;

    @PrePersist
    protected void onCreate() {
        this.postDate = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "post")
    private Set<Media> media; // Một post có thể có nhiều media

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post")
    private Set<LikePost> likes; // Mối quan hệ với bảng Like
}