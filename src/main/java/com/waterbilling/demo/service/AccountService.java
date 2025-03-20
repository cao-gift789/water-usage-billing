package com.waterbilling.demo.service;

import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.Role;
import com.waterbilling.demo.repository.AccountRepository;
import com.waterbilling.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Account createAccount(String username, String password, String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            throw new RuntimeException("Role không tồn tại");
        }

        Account account = new Account(username, password, role);
        return accountRepository.save(account);
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
