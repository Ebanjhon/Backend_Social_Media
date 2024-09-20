package com.eban.social_media.Repositories;

import com.eban.social_media.Models.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {

}
