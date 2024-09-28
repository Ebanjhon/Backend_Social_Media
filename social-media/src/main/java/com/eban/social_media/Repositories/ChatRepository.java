package com.eban.social_media.Repositories;

import com.eban.social_media.DTO.UserFollowChatDTO;
import com.eban.social_media.Models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT new com.eban.social_media.DTO.UserFollowChatDTO(" +
            "uf.userFollow.id, uf.userFollow.avatar, uf.userFollow.username) " +
            "FROM UserFollow uf " +
            "WHERE uf.user.id = :userId") // Lấy danh sách người dùng mà người dùng hiện tại đang theo dõi
    List<UserFollowChatDTO> getUsersChatByFollowing(@Param("userId") Long userId);

}
