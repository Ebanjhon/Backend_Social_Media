package com.eban.social_media.DTO;

public class JwtRequest {

    private String username;
    private String password;

    // Constructor mặc định
    public JwtRequest() {
    }

    // Constructor với tham số
    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters và Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}