package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.UserRegistrionRequest;
import com.waterbilling.demo.dto.request.UserUpdateRequest;
import com.waterbilling.demo.dto.response.UserResponse;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.mapper.UserMapper;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserResponse checkUserByIdentityNumber(String identityNumber) {
        User user = userRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    public String registerOrUpdateUser(UserRegistrionRequest request) {

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return userRepository.registerOrUpdateUserWithAccount(
                request.getIdentityNumber(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getFullName(),
                encodedPassword,
                request.getUsername()
        );
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
