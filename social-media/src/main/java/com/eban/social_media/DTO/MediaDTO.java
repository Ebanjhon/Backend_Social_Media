package com.eban.social_media.DTO;

import com.eban.social_media.Models.MediaType;

public class MediaDTO {
    private Long mediaId;
    private String mediaType,mediaUrl;

    // Constructor
    public MediaDTO(MediaType mediaType, String mediaUrl) {
        this.mediaType = mediaType != null ? mediaType.name() : "UNKNOWN"; // Chuyển MediaType thành String
        this.mediaUrl = mediaUrl;
    }

    public MediaDTO(Long mediaId, MediaType mediaType, String mediaUrl) {
        this.mediaId = mediaId;
        this.mediaType = mediaType.toString();
        this.mediaUrl = mediaUrl;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
