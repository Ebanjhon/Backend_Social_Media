package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.ListPostDTO;
import com.eban.social_media.Services.ServiceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class ManaPostsController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/posts")
    public ResponseEntity<List<ListPostDTO>> getPosts(@RequestParam(required = false) String text,
                                                     @RequestParam(defaultValue = "0") int page) {
        // Đặt mỗi trang lấy 20 bài viết
        Pageable pageable = PageRequest.of(page, 20);
        // Gọi tới service để lấy danh sách bài viết với phân trang
        return  ResponseEntity.ok(postService.manaListPosts(text, pageable));
    }
}
