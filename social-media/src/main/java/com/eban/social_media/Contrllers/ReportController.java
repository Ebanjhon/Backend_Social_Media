package com.eban.social_media.Contrllers;


import com.eban.social_media.DTO.ReportDTO;
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
    public ResponseEntity<?> createReport(@RequestBody ReportDTO createReportRequest) {
        try {
            reportService.createReport(
                    createReportRequest.getUserId(),
                    createReportRequest.getPostId(),
                    createReportRequest.getContent()
            );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
