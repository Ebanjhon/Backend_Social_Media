package com.eban.social_media.DTO;

public class ProfileDetailDTO {
    Long countPost, countFollower, countFollowing;

    public ProfileDetailDTO(Long countPost, Long countFollower, Long countFollowing) {
        this.countPost = countPost;
        this.countFollower = countFollower;
        this.countFollowing = countFollowing;
    }

    public ProfileDetailDTO() {
    }

    public Long getCountPost() {
        return countPost;
    }

    public void setCountPost(Long countPost) {
        this.countPost = countPost;
    }

    public Long getCountFollower() {
        return countFollower;
    }

    public void setCountFollower(Long countFollower) {
        this.countFollower = countFollower;
    }

    public Long getCountFollowing() {
        return countFollowing;
    }

    public void setCountFollowing(Long countFollowing) {
        this.countFollowing = countFollowing;
    }
}
