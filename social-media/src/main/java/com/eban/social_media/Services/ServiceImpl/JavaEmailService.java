package com.eban.social_media.Services.ServiceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class JavaEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendOTP(String to, String subject, String otpCode) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(to);
        helper.setSubject(subject);

        // Nội dung HTML của email
        String htmlContent = "<html>" +
                "<head>" +
                "<link rel='preconnect' href='https://fonts.googleapis.com'>" +
                "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>" +
                "<link href='https://fonts.googleapis.com/css2?family=Pacifico&display=swap' rel='stylesheet'>" +
                "</head>" +
                "<body>" +
                "<div style='text-align: center;'>" +
                "<img src='https://storage.googleapis.com/eban_social_media/LOGOPNG.png' alt='Logo' style='width:350px; margin-bottom: 0px;'/>" +
                "<h2 style='font-family: Pacifico, cursive, Arial, sans-serif; font-weight: 400; font-size: 32px;'>Helianthus</h2>" +
                "<h2>Vui lòng không cung cấp Mã OTP cho bất cứ ai</h2>" +
                "<h1 style='color: blue;'>Mã Của Bạn Là: " + otpCode + "</h1>" +
                "</div>" +
                "</body>" +
                "</html>";

        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
