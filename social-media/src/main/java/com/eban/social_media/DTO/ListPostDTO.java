package com.eban.social_media.DTO;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ListPostDTO {
    private Long idUser, idPost;
    private String firstname, lastname, username, content, avatar;
    private boolean following;
    private LocalDateTime dateCreated;
    private List<MediaDTO> medias;

    // contructor
    public ListPostDTO() {}

    public ListPostDTO(Long idUser, Long idPost, String firstname, String lastname, String username, String content, String avatar, boolean following, LocalDateTime dateCreated) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.content = content;
        this.avatar = avatar;
        this.following = following;
        this.dateCreated = dateCreated;
    }

    //    getter and setter

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        following = following;
    }

    public List<MediaDTO> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaDTO> medias) {
        this.medias = medias;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}