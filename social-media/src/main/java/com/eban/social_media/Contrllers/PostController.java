package com.eban.social_media.Contrllers;

import com.eban.social_media.Models.Media;
import com.eban.social_media.Models.Post;
import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.MediaServiceImpl;
import com.eban.social_media.Services.ServiceImpl.PostServiceImpl;
import com.eban.social_media.Services.ServiceImpl.StorageService;
import com.eban.social_media.Services.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public PostController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPost(
            @RequestPart("userId") String uid,
            @RequestPart("content") String content,
            @RequestPart("files") List<MultipartFile> files) throws IOException {

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

        // bắt đầu lưu danh sách media
        // Xử lý tải lên media và liên kết với bài viết
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                // Upload file và lấy URL
                String fileUrl = storageService.uploadFile(file);

                // Tạo đối tượng Media
                Media media = new Media();
                media.setMediaUrl(fileUrl);
                media.setMediaType(getMediaType(file.getContentType()));
                media.setPost(posted); // Liên kết với bài viết đã được lưu

                // Lưu Media vào cơ sở dữ liệu
                mediaService.createMedia(media);
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

}
