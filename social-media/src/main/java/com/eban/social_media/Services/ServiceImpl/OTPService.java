package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.OTP.OTPGenerator;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    // Lưu trữ OTP và thời gian hết hạn
    private Map<String, OTPData> otpStore = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JavaEmailService javaEmailService;

    // Lưu mã OTP với userID và thời gian hết hạn là 1 phút
    public void saveOTP(Long userId) throws MessagingException {
        String otp = OTPGenerator.generateOTP();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(1);
        otpStore.put(userId.toString(), new OTPData(otp, expiryTime));
        javaEmailService.sendOTP(userService.getUserById(userId).getEmail(), "Mã xác nhận OTP", otp);
        // Lên lịch xóa mã OTP sau 1 phút
        scheduler.schedule(() -> otpStore.remove(userId), 1, TimeUnit.MINUTES);
    }

    // check mã OTP
    public Boolean checkOTP(Long userId, String otp) {
        OTPData otpData = otpStore.get(userId.toString());
        if (otpData != null && otpData.getExpiryTime().isAfter(LocalDateTime.now())) {
            if(otp.equals(otpData.getOtp())) {
                userService.activateUser(userId);
                return true;
            }
        }
        return false;
    }

    // Lấy mã OTP dựa trên userID
    public String getOTP(String userId) {
        OTPData otpData = otpStore.get(userId);
        if (otpData != null && otpData.getExpiryTime().isAfter(LocalDateTime.now())) {
            return otpData.getOtp();
        }
        return null; // OTP đã hết hạn hoặc không tồn tại
    }

    // Xóa mã OTP sau khi sử dụng (nếu cần thiết)
    public void deleteOTP(String userId) {
        otpStore.remove(userId);
    }

    // Class để lưu trữ dữ liệu OTP và thời gian hết hạn
    private static class OTPData {
        private String otp;
        private LocalDateTime expiryTime;

        public OTPData(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpiryTime() {
            return expiryTime;
        }
    }
}


//Ưu điểm:
//Đơn giản: Không cần cấu hình cơ sở dữ liệu hoặc hệ thống lưu trữ ngoài.
//Hiệu suất cao: Dữ liệu được lưu ngay trong bộ nhớ, phù hợp cho lưu trữ ngắn hạn.
//Tự quản lý TTL: Bạn có toàn quyền quản lý thời gian hết hạn của dữ liệu.
//Nhược điểm:
//Chỉ lưu trữ tạm thời: Dữ liệu sẽ bị mất nếu ứng dụng khởi động lại.
//Không thích hợp cho môi trường phân tán: Nếu ứng dụng chạy trên nhiều instance, dữ liệu sẽ không được đồng bộ giữa các instance.
//Tổng kết: