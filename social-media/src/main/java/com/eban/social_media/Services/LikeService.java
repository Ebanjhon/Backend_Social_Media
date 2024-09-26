package com.eban.social_media.Services;

public interface LikeService {
    Long likePost(Long userId, Long postId);
    Boolean disLike(Long userId, Long postId);
}
