package com.eban.social_media.DTO;

import java.util.List;

public class UpdatePostDTO {
    private Long postId;
    private String content;
    private List<Long> idMedias;

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Long> getIdMedias() {
        return idMedias;
    }

    public void setIdMedias(List<Long> idMedias) {
        this.idMedias = idMedias;
    }
}
