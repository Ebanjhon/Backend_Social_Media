package com.eban.social_media.DTO;

public class UserFollowChatDTO {
    private Long user_id;
    private String avatar;
    private String user_name;

    public UserFollowChatDTO(Long userId, String avatar, String username) {
        this.user_id = userId;
        this.avatar = avatar;
        this.user_name = username;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

}
