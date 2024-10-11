package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.Models.Post;
import com.eban.social_media.Models.Report;
import com.eban.social_media.Models.User;
import com.eban.social_media.Repositories.ReportRepository;
import com.eban.social_media.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Override
    public void createReport(Long userId, Long postId, String content) {
        Report r = new Report();
        r.setUser(userServiceImpl.getUserById(userId));
        r.setPost(postServiceImpl.getPost(postId));
        r.setContentReport(content);
        reportRepository.save(r);
    }
}
