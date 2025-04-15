package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.ChangePasswordRequest;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    public void changePassword(ChangePasswordRequest request) {

        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();

        Account account = accountRepository.findById( Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(request.getCurrentPassword(), account.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(account);
    }

}
