package com.eban.social_media.Repositories;

import com.eban.social_media.Models.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {
// lấy ma phòng chat
    @Query("SELECT rc.id FROM RoomChat rc " +
            "JOIN rc.chats c1 ON c1.user.id = :firstUserId " +
            "JOIN rc.chats c2 ON c2.user.id = :secondUserId " +
            "WHERE c1.roomChat = c2.roomChat")
    Optional<Long> getRoomChatByTwoUser(@Param("firstUserId") Long firstUserId, @Param("secondUserId") Long secondUserId);

}
