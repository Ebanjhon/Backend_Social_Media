package com.eban.social_media.Repositories;

import com.eban.social_media.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{

}
