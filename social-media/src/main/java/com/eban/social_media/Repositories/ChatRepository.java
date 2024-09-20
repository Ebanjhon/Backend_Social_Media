package com.eban.social_media.Repositories;

import com.eban.social_media.Models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
