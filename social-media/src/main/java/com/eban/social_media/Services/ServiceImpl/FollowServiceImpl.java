package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.Models.User;
import com.eban.social_media.Models.UserFollow;
import com.eban.social_media.Repositories.FollowRepository;
import com.eban.social_media.Services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public Boolean unfollow(Long userId, Long targetUserId) {
        try {
            followRepository.deleteFollow(userId, targetUserId);
            return true;
        } catch (Exception e) {
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    @Override
    public Boolean Following(Long idUser, Long idTargetUser) {
        User user = userServiceImpl.getUserById(idUser);
        User targetUser = userServiceImpl.getUserById(idTargetUser);

        if (user == null || targetUser == null) {
            return false; // Không tìm thấy người dùng
        }

        // Kiểm tra xem đã follow chưa
        boolean alreadyFollowing = followRepository.existsByUserIdAndUserFollowId(idUser, idTargetUser);
        if (!alreadyFollowing) {
            UserFollow userFL = new UserFollow();
            userFL.setUser(user);
            userFL.setUserFollow(targetUser);
            followRepository.save(userFL);
            return true;
        }
        return false;
    }
}
