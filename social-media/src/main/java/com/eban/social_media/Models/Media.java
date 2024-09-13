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

    @Enumerated(EnumType.STRING) // Lưu trữ giá trị enum dưới dạng chuỗi
    @Column(nullable = false)
    private MediaType mediaType; // 'VIDEO', 'IMAGE', hoặc 'AUDIO'

    @Column(nullable = false)
    private String mediaUrl;

    // post có thể có nhiều media
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // khóa tham chiếu đến bảng post
}
