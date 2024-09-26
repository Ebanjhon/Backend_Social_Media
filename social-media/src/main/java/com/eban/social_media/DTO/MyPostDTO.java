package com.eban.social_media.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class MyPostDTO {
    private Long idPost;
    private String content;
    private LocalDateTime dateCreated;
    private List<MediaDTO> medias;
    private Long countLikes;

    public MyPostDTO(Long idPost, String content, LocalDateTime dateCreated, Long countLikes) {
        this.idPost = idPost;
        this.content = content;
        this.dateCreated = dateCreated;
        this.countLikes = countLikes;
    }

    public Long getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(Long countLikes) {
        this.countLikes = countLikes;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<MediaDTO> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaDTO> medias) {
        this.medias = medias;
    }
}
