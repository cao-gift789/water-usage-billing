
package com.waterbilling.demo.controller;

import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.repository.AccountRepository;
import com.waterbilling.demo.service.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

//    @PostMapping("/create")
//    public Account createAccount(@RequestParam String username, 
//                                 @RequestParam String password 
//                                 ) {
//        return accountService.createAccount(username, password);
//    }
    

}

