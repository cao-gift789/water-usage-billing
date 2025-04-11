
package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.request.ChangePasswordRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.repository.AccountRepository;
import com.waterbilling.demo.service.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/change-password")
    public ApiResponse<?> changePassword(@RequestBody ChangePasswordRequest request) {
        accountService.changePassword(request);
        return ApiResponse.<String>builder()
                .result("Đổi mật khẩu thành công")
                .build();
    }

}

