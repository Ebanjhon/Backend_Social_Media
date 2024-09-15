package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.MediaDTO;
import com.eban.social_media.Models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("SELECT m FROM Media m WHERE m.post.idPost = :postId")
    List<Media> findMediaByPostId(@Param("postId") Long postId);
}
