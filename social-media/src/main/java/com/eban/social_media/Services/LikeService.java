package com.eban.social_media.Services;

public interface LikeService {
    Long likePost(Long userId, Long postId) throws Exception;
    Boolean disLike(Long userId, Long postId);
}
