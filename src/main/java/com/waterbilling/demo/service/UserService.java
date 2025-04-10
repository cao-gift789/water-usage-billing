package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.UserRegistrionRequest;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


}
