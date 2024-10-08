package com.eban.social_media.DTO;

import com.eban.social_media.Models.NotifiType;

import java.time.LocalDateTime;

public class NotifiDTO {
    private Long userId, id;
    private String message, avatar, username, firstname, lastname;
    private LocalDateTime dateTime;
    private NotifiType type;

    public NotifiDTO(Long id, Long userId, String avatar, String username, String firstname, String lastname, LocalDateTime dateTime, NotifiType type) {
        this.id = id;
        this.userId = userId;
        this.avatar = avatar;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateTime = dateTime;
        this.type = type;
        switch (type){
            case LIKE:
                this.message = "đã thích bài viết của bạn";
                break;
            case FOLLOW:
                this.message = "đã bắt đầu theo dõi bạn";
                break;
            case COMMENT:
                this.message = "đã bình luận bài viết của bạn";
                break;
            case REPLY_COMMENT:
                this.message = "đã trả lời binh luận của bạn";
                break;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public NotifiType getType() {
        return type;
    }

    public void setType(NotifiType type) {
        this.type = type;
    }
}
