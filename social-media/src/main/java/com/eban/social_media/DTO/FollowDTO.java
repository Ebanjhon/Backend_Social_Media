package com.eban.social_media.DTO;

public class FollowDTO {
    private Long idUser;
    private Long idTargetUser;

    // Getters and Setters
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdTargetUser() {
        return idTargetUser;
    }

    public void setIdTargetUser(Long idTargetUser) {
        this.idTargetUser = idTargetUser;
    }
}
