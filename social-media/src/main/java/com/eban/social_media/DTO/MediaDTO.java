package com.eban.social_media.DTO;

public class MediaDTO {
    private String mediaType,mediaUrl;

    public MediaDTO(String mediaType, String mediaUrl) {
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
    }

    public MediaDTO() {}

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
