package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_follows")
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user; // Người dùng hiện tại

    @ManyToOne
    @JoinColumn(name = "id_user_follow", nullable = false)
    private User userFollow; // Người dùng được theo dõi
}
