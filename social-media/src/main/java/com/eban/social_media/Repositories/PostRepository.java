package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.ListPostDTO;
import com.eban.social_media.DTO.MyPostDTO;
import com.eban.social_media.Models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    @Query("SELECT new com.eban.social_media.DTO.ListPostDTO(p.user.id, p.idPost, p.user.firstname, p.user.lastname, p.user.username, p.content, p.user.avatar, " +
            "CASE WHEN (uf.id IS NOT NULL) THEN true ELSE false END, p.postDate, COUNT(DISTINCT l.id), " +
            "CASE WHEN (COUNT(DISTINCT l2.id) > 0) THEN true ELSE false END) " +
            "FROM Post p " +
            "LEFT JOIN UserFollow uf ON uf.user.id = :currentUserId AND uf.userFollow.id = p.user.id " +
            "LEFT JOIN LikePost l ON l.post.idPost = p.idPost " +
            "LEFT JOIN LikePost l2 ON l2.post.idPost = p.idPost AND l2.user.id = :currentUserId " +
            "GROUP BY p.user.id, p.idPost, p.user.firstname, p.user.lastname, p.user.username, p.content, p.user.avatar, uf.id, p.postDate")
    Page<ListPostDTO> findAllPostsWithMediaAndFollowStatus(@Param("currentUserId") Long currentUserId, Pageable pageable);



    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
    long countPostsByUserId(@Param("userId") Long userId);

    // Truy vấn để lấy danh sách các bài viết và phương tiện truyền thông theo userId
    @Query("SELECT new com.eban.social_media.DTO.MyPostDTO(p.idPost, p.content, p.postDate, " +
            "(SELECT COUNT(l) FROM LikePost l WHERE l.post.idPost = p.idPost)) " +
            "FROM Post p WHERE p.user.id = :userId")
    List<MyPostDTO> getMyPostsByUserId(@Param("userId") Long userId);

    // lấy bài viết đã like
    @Query("SELECT new com.eban.social_media.DTO.ListPostDTO(p.user.id, p.idPost, p.user.firstname, p.user.lastname, p.user.username, p.content, p.user.avatar, " +
            "CASE WHEN (uf.id IS NOT NULL) THEN true ELSE false END, p.postDate, COUNT(DISTINCT l.id), " +
            "CASE WHEN (COUNT(DISTINCT l2.id) > 0) THEN true ELSE false END) " +
            "FROM Post p " +
            "LEFT JOIN UserFollow uf ON uf.user.id = :currentUserId AND uf.userFollow.id = p.user.id " +
            "LEFT JOIN LikePost l ON l.post.idPost = p.idPost " +
            "LEFT JOIN LikePost l2 ON l2.post.idPost = p.idPost AND l2.user.id = :currentUserId " +
            "WHERE EXISTS (SELECT 1 FROM LikePost l3 WHERE l3.post.idPost = p.idPost AND l3.user.id = :currentUserId) " +
            "GROUP BY p.user.id, p.idPost, p.user.firstname, p.user.lastname, p.user.username, p.content, p.user.avatar, uf.id, p.postDate")
    Page<ListPostDTO> getPostLiked(@Param("currentUserId") Long currentUserId, Pageable pageable);

    // đếm số lượng like của bài viết
    @Query("SELECT COUNT(like.id) FROM LikePost like WHERE like.post.idPost = :postId")
    Long countLikePost(@Param("postId") Long postId);

}
