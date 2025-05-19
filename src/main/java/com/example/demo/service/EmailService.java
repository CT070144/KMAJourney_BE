package com.example.demo.service;

import com.example.demo.dto.response.EmailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmailService {

    JavaMailSender mailSender;
    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // OTP 6 chữ số
        return String.valueOf(otp);
    }

    // Gửi OTP đến email người dùng
    public EmailResponse sendOTP(String to) throws MessagingException {
        String subject = "Mã xác minh tài khoản";
        String otp = generateOTP();
        String content = "Mã khôi phục mật khẩu của bạn là : " + otp;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
        return EmailResponse.builder()
                .otp(otp).build();
    }

}
