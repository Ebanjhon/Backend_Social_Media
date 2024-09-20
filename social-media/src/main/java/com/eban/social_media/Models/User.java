package com.eban.social_media.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "users") // tên bảng sẽ được tạo trong cơ sở dữ liệu
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Tự động ánh xạ tới cột id trong bảng

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String avatar;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timeCreated;

    @PrePersist
    protected void onCreate() {
        this.timeCreated = LocalDateTime.now();
    }

    // sẽ xóa dữ liệu nếu user bị hủy
    @OneToMany(mappedBy = "user")
    private Set<LikePost> likes; // Mối quan hệ với bảng Like

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts; // Mối quan hệ với bảng Post

    @Transient
    public Set<String> getRoles() {
        return Set.of(role.name()); // Trả về vai trò của người dùng dưới dạng danh sách
    }

}
