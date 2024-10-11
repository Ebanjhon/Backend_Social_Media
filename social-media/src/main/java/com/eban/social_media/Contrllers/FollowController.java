package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.FollowDTO;
import com.eban.social_media.Services.ServiceImpl.FollowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follow")
@CrossOrigin(origins = "*")
public class FollowController {

    @Autowired
    private FollowServiceImpl followServiceImpl;

    @PostMapping
    public ResponseEntity<String> followUser(@RequestBody FollowDTO followRequestDTO) {
        try {
            Boolean result = followServiceImpl.Following(followRequestDTO.getIdUser(), followRequestDTO.getIdTargetUser());
            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body("Đã theo dỏi ng dùng thành công!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bạn đã theo dỏi người dùng này");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
        }
    }

//    Theo chuẩn REST, các phương thức DELETE không nên có phần thân yêu cầu (request body). Hầu hết các thư viện HTTP (bao gồm cả Spring và một số trình duyệt) không xử lý tốt khi gửi dữ liệu trong thân yêu cầu cho phương thức DELETE.
    @DeleteMapping
    public ResponseEntity<String> unfollowUser(
            @RequestParam Long idUser,
            @RequestParam Long idTargetUser) {

        Boolean result = followServiceImpl.unfollow(idUser, idTargetUser);

        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("User unfollowed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to unfollow user");
        }
    }
}
