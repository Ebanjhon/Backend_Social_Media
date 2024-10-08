package com.eban.social_media.Contrllers;


import com.eban.social_media.Models.User;
import com.eban.social_media.Services.ServiceImpl.OTPService;
import com.eban.social_media.Services.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/OTP")
@CrossOrigin(origins = "*")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<?> sendOTP(@RequestParam Long userId){
        try{
            User user = userServiceImpl.getUserById(userId);
            if(user == null){
                return ResponseEntity.notFound().build();
            }else{
                otpService.saveOTP(user);
                return ResponseEntity.ok().build();
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // API để xác thực mã OTP
    @PostMapping("/validate")
    public ResponseEntity<?> validateOTP(@RequestParam Long userId, @RequestParam String otp) {
        boolean isValid = otpService.validateOTP(userId.toString(), otp);
        if (isValid) {
            userServiceImpl.activateUser(userId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("OTP không hợp lệ hoặc đã hết hạn!");
        }
    }
}
