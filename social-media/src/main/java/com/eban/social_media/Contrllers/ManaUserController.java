package com.eban.social_media.Contrllers;


import com.eban.social_media.DTO.ManaUserDTO;
import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class ManaUserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<ManaUserDTO>> getManaUsers(
            @RequestParam(required = false) String text,
            Pageable pageable) {

        // Lấy danh sách ManaUser từ service
        List<ManaUserDTO> users = userService.getManaUsers(text, pageable);

        // Trả về ResponseEntity với danh sách users và HTTP status OK (200)
        return ResponseEntity.ok(users);
    }

    @PostMapping("/active-account")
    public ResponseEntity<String> activeAccount(@RequestParam Long userID) {
        User u = userService.getUserById(userID);
        if(u == null){
            return ResponseEntity.notFound().build();
        }else{
            userService.activateUser(userID);
            return ResponseEntity.ok("Activated");
        }
    }

    @PostMapping("/unactive-account")
    public ResponseEntity<String> unactiveAccount(@RequestParam Long userID) {
        User u = userService.getUserById(userID);
        if(u == null){
            return ResponseEntity.notFound().build();
        }else{
            userService.unActivateUser(userID);
            return ResponseEntity.ok("Activated");
        }
    }

}
