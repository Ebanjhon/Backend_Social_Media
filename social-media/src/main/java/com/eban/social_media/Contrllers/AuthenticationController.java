package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.AdminDTO;
import com.eban.social_media.DTO.JwtRequest;
import com.eban.social_media.DTO.JwtResponse;
import com.eban.social_media.DTO.UserDTO;
import com.eban.social_media.JWT.JwtUtils;
import com.eban.social_media.Models.Role;
import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.UserServiceDetails;
import com.eban.social_media.Services.ServiceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceDetails userDetailsService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        try {
            // Xác thực người dùng
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            // Trả về 401 Unauthorized nếu username hoặc password không chính xác
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mật khẩu hoặc tên người dùng không đúng!");
        }

        // Nếu xác thực thành công, tạo JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails.getUsername());

        // Trả về JWT token và trạng thái 200 OK
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/admin-auth")
    public ResponseEntity<?> adminAuthentication(@RequestBody JwtRequest authenticationRequest) {
        try {
            // Xác thực người dùng
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập!");
        }

        // Nếu xác thực thành công, tạo JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails.getUsername());
        UserDTO u = userServiceImpl.getUserDTOByUsername(authenticationRequest.getUsername());
        if(u.getRole() == Role.ROLE_ADMIN.toString())
        {
            AdminDTO admin = new AdminDTO(u, jwt);
            return ResponseEntity.ok(admin);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản không phải là quản trị viên!");
        }
    }

    // API để đăng ký người dùng mới
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        if(userServiceImpl.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email đã tồn tại trên hệ thống!", HttpStatus.CONFLICT);
        } else if (userServiceImpl.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Tên người dùng đã có sẵn!", HttpStatus.CONFLICT);
        }else{
            User createdUser = userServiceImpl.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User created successfully with ID: " + createdUser.getId());
        }
    }

}
