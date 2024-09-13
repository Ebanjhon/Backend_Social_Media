package com.eban.social_media.DTO;

public class PostDTO {
    private Long userID;
    private String content;

    public PostDTO(Long userID, String content) {
        this.userID = userID;
        this.content = content;
    }

    public PostDTO() {}

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

