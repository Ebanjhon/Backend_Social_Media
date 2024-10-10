package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.ManaUserDTO;
import com.eban.social_media.DTO.ProfileDetailDTO;
import com.eban.social_media.DTO.SearchUserDTO;
import com.eban.social_media.DTO.UserDTO;
import com.eban.social_media.JWT.JwtUtils;
import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.StorageService;
import com.eban.social_media.Services.ServiceImpl.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private JwtUtils jwtUtils;

    private final StorageService storageService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(StorageService storageService) {
        this.storageService = storageService;
    }

    // API để lấy danh sách tất cả người dùng
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @GetMapping("/curent")
    public ResponseEntity<UserDTO> getUserDetailsFromToken(HttpServletRequest request) {
        // Lấy token từ header Authorization
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // Nếu không có token hoặc token không hợp lệ
        }

        String jwt = authorizationHeader.substring(7);
        String username;

        try {
            username = jwtUtils.extractUsername(jwt);  // Lấy username từ JWT
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // Token hết hạn
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Lỗi xử lý token
        }

        // Lấy thông tin chi tiết của người dùng từ username
        UserDTO user = userServiceImpl.getUserDTOByUsername(username);
        return ResponseEntity.ok(user);  // Trả về thông tin người dùng
    }

    // API để lấy thông tin người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userServiceImpl.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // API để xóa người dùng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchUserDTO>> findUserDTO(@RequestParam String text, @RequestParam Long idUser){
        List<SearchUserDTO> listU = userServiceImpl.SearchUser(text, idUser);
        if(listU.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }else {
           return ResponseEntity.ok(listU);
        }
    }

    @PutMapping("/update-avatar")
    public ResponseEntity<?> updateAvatar(@RequestParam("file") MultipartFile file, @RequestParam Long idUser) {
        try {
            User user = userServiceImpl.getUserById(idUser);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Không tìm thấy người dùng với id: " + idUser);
            } else {
                String fileUrl = storageService.uploadFile(file);
                userServiceImpl.updateAvatar(user, fileUrl);
                return ResponseEntity.ok("Cập nhật avatar thành công!");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi trong quá trình upload file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi không xác định: " + e.getMessage());
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<ProfileDetailDTO> getProfileDetailUser(@RequestParam Long idUser){
        try{
            return ResponseEntity.ok(userServiceImpl.getProfileDetail(idUser));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
        try {
            // Kiểm tra xem user có null hay không
            if (user == null) {
                return new ResponseEntity<>("User data is required", HttpStatus.BAD_REQUEST);
            }
            userServiceImpl.updateUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}