package com.waterbilling.demo.service;

import com.waterbilling.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

//    public void changePassword(ChangePasswordRequest request) {
//        // Lấy email từ context (người dùng đã đăng nhập)
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Tìm account
//        Account account = accountRepository.findByEmail(email);
//        if (account == null) {
//            throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
//        }
//
//        // Kiểm tra mật khẩu hiện tại
//        if (!passwordEncoder.matches(request.getCurrentPassword(), account.getPassword())) {
//            throw new ApplicationException(ErrorCode.INVALID_PASSWORD);
//        }
//
//        // Cập nhật mật khẩu mới
//        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        accountRepository.save(account);
//    }

}
