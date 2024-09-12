package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "media_comments")
public class MediaComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "comment_id", nullable = false, unique = true)
    private Comment commentEntity;

    @Column(nullable = false)
    private String media;
}
