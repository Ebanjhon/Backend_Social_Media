package com.eban.social_media.Contrllers;

import com.eban.social_media.DTO.RequestOTP;
import com.eban.social_media.Services.ServiceImpl.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/OTP")
@CrossOrigin(origins = "*")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping()
    public ResponseEntity<String> sendOTP(@RequestParam Long userId){
        try{
            otpService.saveOTP(userId);
            return new ResponseEntity<>("Tạo thành công!",HttpStatus.OK);
        }catch (Exception e){
         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<String> getOTP(@RequestParam Long userId){
        try{
            String OTP = otpService.getOTP(userId.toString());
            return new ResponseEntity<>(OTP,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/active")
    public ResponseEntity<String> checkOTP(@RequestBody RequestOTP requestOTP){
        if(otpService.checkOTP(requestOTP.getUserId(), requestOTP.getOtp())){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}