package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.CommentDTO;
import com.eban.social_media.DTO.CommentResponseDTO;
import com.eban.social_media.Models.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //lấy các cmt chưa có các cmt con
    @Query("SELECT new com.eban.social_media.DTO.CommentResponseDTO(c.idComment, u.id, c.content, u.avatar, u.firstname, u.lastname, c.timeComment) " +
            "FROM Comment c " +
            "JOIN c.user u " +
            "WHERE c.post.idPost = :postId AND c.parentComment IS NULL " +
            "ORDER BY c.timeComment DESC")
    List<CommentResponseDTO> getCommentParentByPostId(@Param("postId") Long postId);

    // lấy các cmt con
    @Query("SELECT new com.eban.social_media.DTO.CommentDTO(c.idComment, u.id, c.content, u.avatar, u.firstname, u.lastname, c.timeComment) " +
            "FROM Comment c " +
            "JOIN c.user u " +
            "WHERE c.parentComment.idComment = :parentCommentId " +
            "ORDER BY c.timeComment ASC")
    List<CommentDTO> GetCommentChildByParent(@Param("parentCommentId") Long parentCommentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.parentComment.idComment = :idCommentParent OR c.idComment = :idCommentParent")
    void deleteByIdCommentParent(@Param("idCommentParent") Long idCommentParent);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.post.idPost = :idPost")
    void deleteByIdPost(@Param("idPost") Long idPost);
}
