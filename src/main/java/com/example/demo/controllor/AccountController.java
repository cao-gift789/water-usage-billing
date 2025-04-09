package com.example.demo.controllor;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(@RequestParam String username, 
                                 @RequestParam String password, 
                                 @RequestParam String roleName) {
        return accountService.createAccount(username, password, roleName);
    }
}
