package com.eban.social_media.DTO;

public class RequestOTP {
    Long userId;
    String otp;

    public RequestOTP(Long userId, String otp) {
        this.userId = userId;
        this.otp = otp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
