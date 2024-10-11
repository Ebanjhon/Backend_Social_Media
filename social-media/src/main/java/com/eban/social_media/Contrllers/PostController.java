package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.LikeDTO;
import com.eban.social_media.DTO.ListPostDTO;
import com.eban.social_media.DTO.MyPostDTO;
import com.eban.social_media.Models.Media;
import com.eban.social_media.Models.Post;
import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MediaServiceImpl mediaService;

    private final StorageService storageService;
    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @Autowired
    private ImageService imageService;

    @Autowired
    private LikeServiceImpl likeServiceImpl;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    public PostController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<Page<ListPostDTO>> getListPost(
            @RequestParam Long currentUserId,
            @PageableDefault(size = 10) Pageable pageable) { // Sử dụng Pageable để phân trang

        // Nhận đối tượng Page từ service
        Page<ListPostDTO> posts = postService.getListPostsDTO(currentUserId, pageable);

        // Kiểm tra nếu không có dữ liệu trả về
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Trả về đối tượng Page trực tiếp
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPost(
            @RequestPart("userId") String uid,
            @RequestPart("content") String content,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {

        // Chuyển đổi userId từ String sang Long
        Long userId = Long.parseLong(uid);

        // tìm kiếm id user có trên hệ thông hsya không
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy tài khoản!");
        }
        // Tạo đối tượng post mới
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        post.setPostDate(LocalDateTime.now());

        // Lưu bài đăng vào cơ sở dữ liệu
        Post posted = postService.savePost(post);

        // bắt đầu lưu danh sách media, check có file hay không?
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                // Xác định loại file dựa trên content type(loại ảnh)
                com.eban.social_media.Models.MediaType mediaType = getMediaType(file.getContentType());

                // nếu là dạng ảnh
                if (mediaType == com.eban.social_media.Models.MediaType.IMAGE) {

                    // kích thước tôi đã của hình ảnh là 800px x 600px
                    file = imageService.resizeImage(file);

                    // Upload file vào GCP và nhận URL ảnh
                    String fileUrl = storageService.uploadFile((MultipartFile) file);

                    // Tạo đối tượng Media và lưu vào DB
                    Media media = new Media();
                    media.setMediaUrl(fileUrl);
                    media.setMediaType(mediaType);
                    media.setPost(posted);
                    mediaService.createMedia(media);

                } else {
                    // Không resize, chỉ upload file không phải ảnh (như video hoặc âm thanh)
                    String fileUrl = storageService.uploadFile(file);

                    // Tạo đối tượng Media và lưu vào DB
                    Media media = new Media();
                    media.setMediaUrl(fileUrl);
                    media.setMediaType(mediaType); // Loại media có thể là video, âm thanh
                    media.setPost(posted);
                    mediaService.createMedia(media);
                }
            }
        }
        return ResponseEntity.ok("Đăng bài viết thành công!");
    }

    private com.eban.social_media.Models.MediaType getMediaType(String contentType) {
        if (contentType != null) {
            if (contentType.startsWith("video/")) {
                return com.eban.social_media.Models.MediaType.VIDEO;
            } else if (contentType.startsWith("image/")) {
                return com.eban.social_media.Models.MediaType.IMAGE;
            } else if (contentType.startsWith("audio/")) {
                return com.eban.social_media.Models.MediaType.AUDIO;
            }
        }
        return null;
    }

    @DeleteMapping
    public ResponseEntity<String> deletePost(@RequestParam Long postId) {
        try {
            // xóa media
            mediaService.deleteMediaByPostId(postId);
            //xóa thích
            likeServiceImpl.deletePost(postId);
            // xóa comment
            commentServiceImpl.deleteCommentByPostId(postId);
            // Xóa bài viết
            postService.deletePost(postId);

            return ResponseEntity.ok("Post and associated comments deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/myposts")
    public ResponseEntity<List<MyPostDTO>> getMyPosts(@RequestParam Long idUser){
        try{
            return ResponseEntity.ok(postService.getMyPost(idUser));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/posts-liked")
    public ResponseEntity<Page<ListPostDTO>> getPostLiked(@RequestParam Long idUser,@PageableDefault(size = 10) Pageable pageable){
        // Nhận đối tượng Page từ service
        Page<ListPostDTO> posts = postService.getPostLiked(idUser, pageable);

        // Kiểm tra nếu không có dữ liệu trả về
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // Trả về đối tượng Page trực tiếp
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PostMapping("/like-post")
    public ResponseEntity<LikeDTO> likePost(@RequestParam Long userId, @RequestParam Long postId) {
        try{
            likeServiceImpl.likePost(userId, postId);
            Long countLike = postServiceImpl.countLike(postId);
            LikeDTO likedto = new LikeDTO();
            likedto.setPostId(postId);
            likedto.setCountLike(countLike);
            return ResponseEntity.ok(likedto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/like-post")
    public ResponseEntity<LikeDTO> disLikePost(@RequestParam Long userId, @RequestParam Long postId) {
        try{
            likeServiceImpl.disLike(userId, postId);
            Long countLike = postServiceImpl.countLike(postId);
            LikeDTO likedto = new LikeDTO();
            likedto.setPostId(postId);
            likedto.setCountLike(countLike);
            return ResponseEntity.ok(likedto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePost(@RequestParam Long postId,@RequestParam String content, @RequestParam List<Long> idMedias) {
        try {
            postServiceImpl.updatePost(postId, content);
            for(Long id: idMedias)
            {
                mediaService.deleteMediaByMediaId(id);
            }
            return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Cập nhật thất bại",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}