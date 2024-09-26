package com.eban.social_media.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "chat_rooms")
public class RoomChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự động sinh

    @Column(nullable = true)
    private String name; // Tên phòng trò chuyện

    @OneToMany(mappedBy = "roomChat")
    private List<Chat> chats; // Mối quan hệ một-nhiều với bảng Chat
}
