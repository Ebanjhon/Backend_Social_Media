package com.eban.social_media.Repositories;

import com.eban.social_media.Models.UserFollow;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<UserFollow, Long> {
    // Kiểm tra xem user đã follow user khác chưa
    boolean existsByUserIdAndUserFollowId(Long userId, Long userFollowId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserFollow uf WHERE uf.user.id = :userId AND uf.userFollow.id = :targetUserId")
    void deleteFollow(Long userId, Long targetUserId);

}
