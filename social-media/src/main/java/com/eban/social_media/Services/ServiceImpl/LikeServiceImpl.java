package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.Models.LikePost;
import com.eban.social_media.Models.NotifiType;
import com.eban.social_media.Models.Post;
import com.eban.social_media.Models.User;
import com.eban.social_media.Repositories.LikePostRepository;
import com.eban.social_media.Services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikePostRepository likePostRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    @Override
    public Long likePost(Long userId, Long postId) throws Exception {
        User u = userServiceImpl.getUserById(userId);
        Post p = postServiceImpl.getPost(postId);
        LikePost like = new LikePost();
        like.setUser(u);
        like.setPost(p);
        if(userId != p.getUser().getId() ){
            notificationServiceImpl.createNotification(userId, p.getUser().getId(), NotifiType.LIKE);
        }
        return likePostRepository.save(like).getId();
    }

    @Override
    public Boolean disLike(Long userId, Long postId) {
        try {
            LikePost likePost = likePostRepository.findLikePostByUserIdAndPostId(userId, postId);
            likePostRepository.delete(likePost);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
