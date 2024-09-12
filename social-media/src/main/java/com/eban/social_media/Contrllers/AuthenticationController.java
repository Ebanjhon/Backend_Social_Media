package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.JwtRequest;
import com.eban.social_media.DTO.JwtResponse;
import com.eban.social_media.JWT.JwtUtils;
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
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            // Xác thực người dùng
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        // Nếu xác thực thành công, tạo JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails.getUsername());

        // Trả về JWT token
        return new JwtResponse(jwt);
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
