package com.eban.social_media.Contrllers;

import com.eban.social_media.Services.ServiceImpl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {
//
//    private final StorageService storageService;
//
//    @Autowired
//    public UploadController(StorageService storageService) {
//        this.storageService = storageService;
//    }

//    @PostMapping
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            // Upload file and get the public URL
//            String fileUrl = storageService.uploadFile(file);
//            return fileUrl;// trả về đường dẫn media
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "FILE ERROR!";
//        }
//    }
}
