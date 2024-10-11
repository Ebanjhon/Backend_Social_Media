package com.eban.social_media.Services;

import com.eban.social_media.DTO.ListPostDTO;
import com.eban.social_media.DTO.MyPostDTO;
import com.eban.social_media.Models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    void updatePost(Post post);
    List<Post> getListPosts();
    Page<ListPostDTO> getListPostsDTO(Long idUser, Pageable pageable);
    Post getPost(Long id);
    void deletePost(Long id);
    List<MyPostDTO> getMyPost(Long userId);
    Page<ListPostDTO> getPostLiked(Long userId, Pageable pageable);
    Long countLike(Long postId);
    List<ListPostDTO> manaListPosts(String text, Pageable pageable);
    void updatePost(Long postId, String content);
}
