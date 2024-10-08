package com.eban.social_media.Services;

public interface FollowService {
    Boolean unfollow(Long userId, Long targetUserId);
    Boolean Following(Long userFollow, Long userfollowing) throws Exception;
}
