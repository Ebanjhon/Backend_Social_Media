package com.eban.social_media.Contrllers;

import com.eban.social_media.Services.ServiceImpl.JavaEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")  // Cho phép mọi nguồn gốc có thể truy cập API này
public class IndexContrller {

    @Autowired
    private JavaEmailService javaEmailService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }



    @PostMapping("/test")
    public ResponseEntity<String> uploadMedia(@RequestParam("file") MultipartFile file) {
        String mediaType = determineMediaType(file.getContentType());

        if (mediaType == null) {
            return ResponseEntity.badRequest().body("Unsupported media type");
        }

        return ResponseEntity.ok("File type: " + mediaType);
    }

    private String determineMediaType(String contentType) {
        if (contentType != null) {
            if (contentType.startsWith("video/")) {
                return "VIDEO";
            } else if (contentType.startsWith("image/")) {
                return "IMAGE";
            } else if (contentType.startsWith("audio/")) {
                return "AUDIO";
            }
        }
        return null;
    }

//    @PostMapping("/send-email")
//    public ResponseEntity<?> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body){
//        try{
//            javaEmailService.sendSimpleMessage(to, subject, body);
//            return ResponseEntity.ok("Email sent successfully");
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PostMapping("/send-OTP")
    public ResponseEntity<?> sendOTP(@RequestParam String to, @RequestParam String subject, @RequestParam String body){
        try{
            javaEmailService.sendOTP(to, subject, body);
            return ResponseEntity.ok("Email sent successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}