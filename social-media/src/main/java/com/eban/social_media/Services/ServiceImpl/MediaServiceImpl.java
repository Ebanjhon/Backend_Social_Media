package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.Models.Media;
import com.eban.social_media.Repositories.MediaRepository;
import com.eban.social_media.Services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
