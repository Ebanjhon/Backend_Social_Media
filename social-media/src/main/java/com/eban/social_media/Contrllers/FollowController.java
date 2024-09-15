package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.FollowDTO;
import com.eban.social_media.Services.FollowService;
import com.eban.social_media.Services.ServiceImpl.FollowServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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
                return ResponseEntity.status(HttpStatus.OK).body("User followed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to follow user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> unfollowUser(@RequestBody FollowDTO followRequestDTO) {
        Boolean result = followServiceImpl.unfollow(followRequestDTO.getIdUser(), followRequestDTO.getIdTargetUser());
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body("User unfollowed successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to unfollow user");
        }
    }
}
