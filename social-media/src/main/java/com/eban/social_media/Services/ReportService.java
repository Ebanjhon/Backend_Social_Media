package com.eban.social_media.Services;

import com.eban.social_media.Models.Report;

public interface ReportService {
    void createReport(Long userId, Long postId, String content);
}
