package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.DTO.MediaDTO;
import com.eban.social_media.Models.Media;
import com.eban.social_media.Repositories.MediaRepository;
import com.eban.social_media.Services.MediaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public void createMedia(Media media) {
        mediaRepository.save(media);
    }

    @Override
    public void updateMedia(Media media) {
        mediaRepository.save(media);
    }

    @Override
    public List<Media> findMediaByPostId(Long postId) {
        return mediaRepository.findMediaByPostId(postId);
    }

    @Override
    public Page<MediaDTO> getMediaByPostId(Long userId, Pageable pageable) {
        return mediaRepository.getMediasByUserId(userId, pageable);
    }

    @Override
    public void deleteMediaByPostId(Long postId) {
        mediaRepository.deleteMediaByPostId(postId);
    }

    @Override
    @Transactional
    public void deleteMediaByMediaId(Long mediaId) {
        mediaRepository.deleteById(mediaId);
    }
}
