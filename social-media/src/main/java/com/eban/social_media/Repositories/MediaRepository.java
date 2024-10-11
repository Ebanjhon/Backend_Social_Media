package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.MediaDTO;
import com.eban.social_media.Models.Media;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("SELECT m FROM Media m WHERE m.post.idPost = :postId")
    List<Media> findMediaByPostId(@Param("postId") Long postId);

    // Truy vấn sử dụng JPQL với hỗ trợ phân trang trả về MediaDTO
    @Query("SELECT new com.eban.social_media.DTO.MediaDTO(m.idMedia, m.mediaType, m.mediaUrl) " +
            "FROM Media m WHERE m.post.user.id = :userId")
    Page<MediaDTO> getMediasByUserId(@Param("userId") Long userId, Pageable pageable);

    // xóa theo idpost
    @Modifying
    @Transactional
    @Query("DELETE FROM Media m WHERE m.post.idPost = :postId")
    void deleteMediaByPostId(@Param("postId") Long postId);

}
