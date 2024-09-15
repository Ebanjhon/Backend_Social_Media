package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.CommentCreateDTO;
import com.eban.social_media.DTO.CommentDTO;
import com.eban.social_media.DTO.CommentResponseDTO;
import com.eban.social_media.Services.CommentService;
import com.eban.social_media.Services.ServiceImpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentCreateDTO commentCreateDTO) {
        try{
            commentService.saveComment(commentCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tạo dữ liệu thành công!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getAllCommentsByPostId(@PathVariable Long postId) {
        try {
            List<CommentResponseDTO> comments = commentService.getCommentByPostId(postId);
            if (comments.isEmpty()) {
                // Trả về 204 No Content nếu không có comment nào
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No comments found for post with id: " + postId);
            }
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (Exception e) {
            // Trả về thông tin lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/postId/{postId}")
    public ResponseEntity<String> deleteCommentsByPostId(@PathVariable Long postId) {
        try {
            // Xóa tất cả bình luận của một bài post
            commentService.deleteCommentByPostId(postId);
            return ResponseEntity.ok("All comments for post deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteCommentsByCommentId(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.ok("Đã xóa comment thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}
