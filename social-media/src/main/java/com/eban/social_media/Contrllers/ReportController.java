package com.eban.social_media.Contrllers;


import com.eban.social_media.Services.ServiceImpl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @PostMapping
    public ResponseEntity<?> createReport(@RequestParam Long userId, @RequestParam Long postId, @RequestParam String content ){
        try{
            reportService.createReport(userId, postId, content);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
