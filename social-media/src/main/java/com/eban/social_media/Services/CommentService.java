package com.eban.social_media.Services;

import com.eban.social_media.DTO.CommentCreateDTO;
import com.eban.social_media.DTO.CommentResponseDTO;
import com.eban.social_media.Models.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(CommentCreateDTO comment);
    Comment getCommentByIdComment(Long id);
    List<CommentResponseDTO> getCommentByPostId(Long postId);
    void deleteComment(Long id);
    void deleteCommentByPostId(Long postId);
    void deletaCommentByCommentIdParent(Long parentId);
}
