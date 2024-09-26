package com.eban.social_media.DTO;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ListPostDTO {
    private Long idUser, idPost;
    private String firstname, lastname, username, content, avatar;
    private boolean following, isLiked;
    private LocalDateTime dateCreated;
    private List<MediaDTO> medias;
    private Long likes;

    // Constructor đầy đủ
    public ListPostDTO(Long idUser, Long idPost, String firstname, String lastname,
                       String username, String content, String avatar, boolean following,
                       LocalDateTime dateCreated, Long likes, boolean isLiked) {
        this.idUser = idUser;
        this.idPost = idPost;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.content = content;
        this.avatar = avatar;
        this.following = following;
        this.dateCreated = dateCreated;
        this.likes = likes;
        this.isLiked = isLiked;
    }

    //    getter and setter

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
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

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
}