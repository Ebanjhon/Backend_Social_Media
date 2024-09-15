package com.eban.social_media.Services;

public interface FollowService {
    Boolean unfollow(Long userFollow, Long userfollowing);
    Boolean Following(Long userFollow, Long userfollowing);
}
