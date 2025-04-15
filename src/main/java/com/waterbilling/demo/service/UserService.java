package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.UserRegistrionRequest;
import com.waterbilling.demo.dto.request.UserUpdateRequest;
import com.waterbilling.demo.dto.response.UserResponse;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.mapper.UserMapper;
import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.Role;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.repository.AccountRepository;
import com.waterbilling.demo.repository.RoleRepository;
import com.waterbilling.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponse checkUserByIdentityNumber(String identityNumber) {
        User user = userRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @Transactional
    public String registerOrUpdateUser(UserRegistrionRequest request) {

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        if (accountRepository.countByUsername(request.getUsername()) > 0) {
            throw new IllegalArgumentException("User name đã tồn tại");
        }

        // Find "User" role
        Role role = roleRepository.findByRoleName("User")
                .orElseThrow(()-> new IllegalArgumentException("Role ko tồn tại"));


        int userCount = userRepository.countByIdentityNumber(request.getIdentityNumber());

        if (userCount > 0) {
            User existingUser = userRepository.findByIdentityNumber(request.getIdentityNumber()).get();

            if (existingUser.getAccountId() == null) {
                Account account = new Account();
                account.setUsername(request.getUsername());
                account.setPassword(encodedPassword);
                account.setRole(role);
                account.setRegistrationDate(LocalDateTime.now());

                account = accountRepository.save(account);
                existingUser.setAccountId(account.getAccountId());
                userRepository.save(existingUser);

                return "User updated with new account successfully";
            } else {
                throw new IllegalArgumentException("User already has an account") ;
            }
        } else {
            // Create new account
            Account account = new Account();
            account.setUsername(request.getUsername());
            account.setPassword(encodedPassword);
            account.setRole(role);
            account.setRegistrationDate(LocalDateTime.now());

            account = accountRepository.save(account);

            User user = new User();
            user.setIdentityNumber(request.getIdentityNumber());
            user.setIsActive(true);
            user.setEmail(request.getEmail());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setFullName(request.getFullName());
            user.setAccountId(account.getAccountId());

            userRepository.save(user);
            return "User and account created successfully";
        }
    }

    public UserResponse checkUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(Integer userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }



}
