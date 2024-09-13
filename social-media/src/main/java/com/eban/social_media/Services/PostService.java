package com.eban.social_media.Services;

import com.eban.social_media.Models.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post);

    void updatePost(Post post);
}
