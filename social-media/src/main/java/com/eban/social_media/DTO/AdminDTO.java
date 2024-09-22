package com.eban.social_media.DTO;

public class AdminDTO {
    UserDTO user;
    String token;

    public AdminDTO(UserDTO user, String token) {
        this.user = user;
        this.token = token;
    }

    public AdminDTO() {}

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
