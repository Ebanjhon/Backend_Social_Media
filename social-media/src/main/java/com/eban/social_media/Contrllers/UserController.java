package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.UserDTO;
import com.eban.social_media.JWT.JwtUtils;
import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtUtils jwtUtils;

    // API để lấy danh sách tất cả người dùng
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
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
        UserDTO user = userService.getUserDTOByUsername(username);
        return ResponseEntity.ok(user);  // Trả về thông tin người dùng
    }

    // API để lấy thông tin người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // API để xóa người dùng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}