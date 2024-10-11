package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.DTO.ListPostDTO;
import com.eban.social_media.DTO.MediaDTO;
import com.eban.social_media.DTO.MyPostDTO;
import com.eban.social_media.Models.Post;
import com.eban.social_media.Repositories.CommentRepository;
import com.eban.social_media.Repositories.MediaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eban.social_media.Repositories.PostRepository;
import com.eban.social_media.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Post savePost(Post post) {
        return  postRepository.save(post);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> getListPosts() {
        return postRepository.findAll();
    }

    public Page<ListPostDTO> getListPostsDTO(Long currentUserId, Pageable pageable) {
        // Bước 1: Lấy danh sách bài đăng mà không có media
        Page<ListPostDTO> postsPage = postRepository.findAllPostsWithMediaAndFollowStatus(currentUserId, pageable);

        // Bước 2: Lấy media cho từng bài đăng và ánh xạ vào DTO
        postsPage.forEach(postDTO -> {
            // Lấy danh sách media từ MediaRepository


            List<MediaDTO> mediaDTOs = mediaRepository.findMediaByPostId(postDTO.getIdPost())
                    .stream()
                    .map(media -> new MediaDTO(media.getIdMedia(), media.getMediaType(), media.getMediaUrl()))
                    .collect(Collectors.toList());

            // Gán danh sách media vào DTO
            postDTO.setMedias(mediaDTOs);
        });

        return postsPage;
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.getReferenceById(id);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<MyPostDTO> getMyPost(Long userId) {
        List<MyPostDTO> myPosts =  postRepository.getMyPostsByUserId(userId);

        myPosts.forEach(p -> {
            List<MediaDTO> mediaDTOs = mediaRepository.findMediaByPostId(p.getIdPost())
                    .stream()
                    .map(media -> new MediaDTO(media.getMediaType(), media.getMediaUrl()))
                    .collect(Collectors.toList());
            p.setMedias(mediaDTOs);
        });
        return myPosts;
    }

    @Override
    public Page<ListPostDTO> getPostLiked(Long userId, Pageable pageable) {
        // Bước 1: Lấy danh sách bài đăng mà không có media
        Page<ListPostDTO> postsPage = postRepository.getPostLiked(userId, pageable);

        // Bước 2: Lấy media cho từng bài đăng và ánh xạ vào DTO
        postsPage.forEach(postDTO -> {
            // Lấy danh sách media từ MediaRepository
            List<MediaDTO> mediaDTOs = mediaRepository.findMediaByPostId(postDTO.getIdPost())
                    .stream()
                    .map(media -> new MediaDTO(media.getMediaType(), media.getMediaUrl()))
                    .collect(Collectors.toList());

            // Gán danh sách media vào DTO
            postDTO.setMedias(mediaDTOs);
        });

        return postsPage;
    }

    @Override
    public Long countLike(Long postId) {
        return postRepository.countLikePost(postId);
    }

    @Override
    public List<ListPostDTO> manaListPosts(String text, Pageable pageable) {
        // Lấy danh sách bài viết có phân trang từ postRepository
        Page<ListPostDTO> postsPage = postRepository.manaListPost(text, pageable);

        // Lấy danh sách bài viết từ Page
        List<ListPostDTO> posts = postsPage.getContent();

        // Ánh xạ media cho từng post
        posts.forEach(postDTO -> {
            // Lấy danh sách media từ MediaRepository
            List<MediaDTO> mediaDTOs = mediaRepository.findMediaByPostId(postDTO.getIdPost())
                    .stream()
                    .map(media -> new MediaDTO(media.getMediaType(), media.getMediaUrl()))
                    .collect(Collectors.toList());

            // Thiết lập danh sách media cho post
            postDTO.setMedias(mediaDTOs);
        });

        return posts;
    }

    @Override
    @Transactional
    public void updatePost(Long postId, String content) {
        Post post = postRepository.getReferenceById(postId);
        post.setContent(content);
        postRepository.save(post);
    }

}
