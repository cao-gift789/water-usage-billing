package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.ChangePasswordOtpRequest;
import com.waterbilling.demo.dto.request.OtpRequest;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.Otp;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.repository.AccountRepository;
import com.waterbilling.demo.repository.OtpRepository;
import com.waterbilling.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class OtpService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    public void generateAndSendOtp(OtpRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        String otpCode = generateOtp();

        Otp otp = Otp.builder()
                .otpCode(otpCode)
                .email(request.getEmail())
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plus(5, ChronoUnit.MINUTES))
                .build();

        otpRepository.save(otp);

        sendOtpEmail(request.getEmail(), otpCode);
    }

    public void changePasswordWithOtp(ChangePasswordOtpRequest request) {
        Otp otp = otpRepository.findByEmailAndOtpCode(request.getEmail(), request.getOtp())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_OTP));

        if (otp.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new AppException(ErrorCode.OTP_EXPIRED);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Account account = accountRepository.findById(user.getAccountId())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(account);

        otpRepository.delete(otp);
    }

    private void sendOtpEmail(String email, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Mã OTP để đổi mật khẩu");
        message.setText("Mã OTP của bạn là: " + otpCode + ". Mã này có hiệu lực trong 5 phút.");
        mailSender.send(message);
    }

    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
