package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.DTO.ListPostDTO;
import com.eban.social_media.DTO.MediaDTO;
import com.eban.social_media.Models.Post;
import com.eban.social_media.Repositories.CommentRepository;
import com.eban.social_media.Repositories.MediaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eban.social_media.Repositories.PostRepository;
import com.eban.social_media.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                    .map(media -> new MediaDTO(media.getMediaType(), media.getMediaUrl()))
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

}
