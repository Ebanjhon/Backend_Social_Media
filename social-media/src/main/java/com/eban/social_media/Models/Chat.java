package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự động sinh

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Khóa ngoại tham chiếu đến bảng User

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private RoomChat roomChat; // Khóa ngoại tham chiếu đến bảng ChatRoom
}