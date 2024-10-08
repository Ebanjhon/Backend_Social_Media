package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.Models.User;
import com.eban.social_media.OTP.OTPGenerator;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OTPService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JavaEmailService emailService;

    private static final long OTP_EXPIRATION_MINUTES = 1; // thời gian của OTP

    public void saveOTP(User user) throws MessagingException {
        // Lưu OTP với userId vào Redis và đặt thời gian hết hạn
        String otp = OTPGenerator.generateOTP();
        emailService.sendOTP(user.getEmail(),"Mã xác thực OTP Helianthus", otp);
        redisTemplate.opsForValue().set(user.getId().toString(), otp, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);
    }

    public boolean validateOTP(String userId, String otp) {
        String storedOTP = redisTemplate.opsForValue().get(userId);
        if (storedOTP != null && storedOTP.equals(otp)) {

            redisTemplate.delete(userId);
            return true;
        }
        return false;
    }
}