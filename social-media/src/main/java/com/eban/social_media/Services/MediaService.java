package com.eban.social_media.Services;

import com.eban.social_media.DTO.MediaDTO;
import com.eban.social_media.Models.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediaService{
    void createMedia(Media media);
    void updateMedia(Media media);
    List<Media> findMediaByPostId(Long postId);
    Page<MediaDTO> getMediaByPostId(Long userId, Pageable pageable);
    void deleteMediaByPostId(Long postId);
    void deleteMediaByMediaId(Long mediaId);
}
