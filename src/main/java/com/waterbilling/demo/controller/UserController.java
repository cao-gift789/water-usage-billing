package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.request.UserRegistrionRequest;
import com.waterbilling.demo.dto.request.UserUpdateRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.dto.response.UserResponse;
import com.waterbilling.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<?> registerUser(@Valid @RequestBody UserRegistrionRequest request) {

        String result = userService.registerOrUpdateUser(request);
        return ApiResponse.<String>builder().result(result).build();
    }

    // api -> check/identityNumber=123456789
    @GetMapping("/check")
    public ApiResponse<?> findUserByIdentityNumber(@RequestParam String identityNumber) {

        return ApiResponse.<UserResponse>builder()
                .result(userService.checkUserByIdentityNumber(identityNumber))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<?> findUserById(@PathVariable("id") Integer id) {

        return ApiResponse.<UserResponse>builder()
                .result(userService.checkUserById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<?> updateUser(@PathVariable Integer id, @Valid @RequestBody UserUpdateRequest request) {

        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, request))
                .build();
    }

}
