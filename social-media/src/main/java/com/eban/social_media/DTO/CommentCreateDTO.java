package com.eban.social_media.DTO;

public class CommentCreateDTO {
    Long idUser, idCmtParent, idPost;
    String content;

    public CommentCreateDTO(Long idUser, Long idCmtParent, Long idPost, String content) {
        this.idUser = idUser;
        this.idCmtParent = idCmtParent;
        this.idPost = idPost;
        this.content = content;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdCmtParent() {
        return idCmtParent;
    }

    public void setIdCmtParent(Long idCmtParent) {
        this.idCmtParent = idCmtParent;
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
}
