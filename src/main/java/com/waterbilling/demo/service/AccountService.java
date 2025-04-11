package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.AccountUserRequest;
import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.Role;
import com.waterbilling.demo.repository.AccountRepository;
import com.waterbilling.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    public boolean createAccount(AccountUserRequest request) {
//
//        if (accountRepository.existsByUsername(request.getUsername())) {
//            throw new RuntimeException("Username đã tồn tại");
//        }
//
//        Role role = roleRepository.findByName("USER")
//                .orElseThrow(() -> new RuntimeException("Role USER không tồn tại"));
//
//        Account account = Account.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword())) // nhớ mã hóa mật khẩu
//                .role(role)
//                .build();
//
//        try {
//            accountRepository.save(account);
//            return true;
//        }catch(DataIntegrityViolationException exception) {
//
//        }
//
//        return
//    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    public boolean findAccount(String username, String password) {
    	if(accountRepository.findByUsernameAndPassword(username,password) != null){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
