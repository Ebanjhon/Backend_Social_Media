package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.Models.Post;
import com.eban.social_media.Repositories.PostRepository;
import com.eban.social_media.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        return  postRepository.save(post);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }
}
