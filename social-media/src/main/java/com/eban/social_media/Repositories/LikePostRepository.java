package com.eban.social_media.Repositories;

import com.eban.social_media.Models.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    @Query("SELECT lp FROM LikePost lp WHERE lp.user.id = :userId AND lp.post.id = :postId")
    LikePost findLikePostByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);
}
