package com.eban.social_media.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseDTO extends CommentDTO {
    private List<CommentDTO> commentChild;

    public CommentResponseDTO(Long idComment, Long idUser, String comment, String avatar, String firstname, String lastname, LocalDateTime commentDate) {
        super(idComment, idUser, comment, avatar, firstname, lastname, commentDate);
    }

    public List<CommentDTO> getCommentChild() {
        return commentChild;
    }

    public void setCommentChild(List<CommentDTO> commentChild) {
        this.commentChild = commentChild;
    }
}
