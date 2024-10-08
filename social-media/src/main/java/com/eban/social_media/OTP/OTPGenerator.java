package com.eban.social_media.OTP;

import java.security.SecureRandom;

public class OTPGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int OTP_LENGTH = 6;

    public static String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(secureRandom.nextInt(10)); // Tạo số ngẫu nhiên từ 0-9
        }
        return otp.toString();
    }
}
