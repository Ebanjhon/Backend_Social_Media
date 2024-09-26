package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.ListPostDTO;
import com.eban.social_media.DTO.MediaDTO;
import com.eban.social_media.Services.ServiceImpl.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*")
public class MediaController {

    @Autowired
    private MediaServiceImpl mediaService;

    @GetMapping
    public ResponseEntity<Page<MediaDTO>> getListPost(
            @RequestParam Long userId,
            @PageableDefault(size = 20) Pageable pageable) {

        Page<MediaDTO> medias = mediaService.getMediaByPostId(userId, pageable);

        // Kiểm tra nếu không có dữ liệu trả về
        if (medias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medias, HttpStatus.OK);
    }
}
