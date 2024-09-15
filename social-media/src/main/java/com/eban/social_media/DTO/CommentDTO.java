package com.eban.social_media.DTO;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long idComment, idUser;
    private String comment, avatar, firstname, lastname;
    private LocalDateTime commentDate;

    public CommentDTO(Long idComment, Long idUser, String comment, String avatar, String firstname, String lastname, LocalDateTime commentDate) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.comment = comment;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.commentDate = commentDate;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }
}
