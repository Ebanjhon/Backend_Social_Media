package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.ListPostDTO;
import com.eban.social_media.Models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>{
    @Query("SELECT new com.eban.social_media.DTO.ListPostDTO(p.user.id, p.idPost, p.user.firstname, p.user.lastname, p.user.username, p.content, p.user.avatar, " +
            "CASE WHEN (uf.id IS NOT NULL) THEN true ELSE false END, p.postDate) " +
            "FROM Post p " +
            "LEFT JOIN UserFollow uf ON uf.user.id = :currentUserId AND uf.userFollow.id = p.user.id")
    Page<ListPostDTO> findAllPostsWithMediaAndFollowStatus(@Param("currentUserId") Long currentUserId, Pageable pageable);

}
