package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedia; // Tự động ánh xạ tới cột id trong bảng

    @Column(nullable = false)
    private String media;

    // post có thể có nhiều media
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // khóa tham chiếu đến bảng post
}
