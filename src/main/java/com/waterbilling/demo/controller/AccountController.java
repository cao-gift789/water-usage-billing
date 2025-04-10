//package com.waterbilling.demo.controller;
//
//import com.waterbilling.demo.dto.request.AccountUserRequest;
//import com.waterbilling.demo.model.Account;
//import com.waterbilling.demo.repository.AccountRepository;
//import com.waterbilling.demo.service.AccountService;
//
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/account")
//public class AccountController {
//
//	@Autowired
//	private AccountRepository accountRepository;
//    @Autowired
//    private AccountService accountService;
//
////    @PostMapping("/create")
////    public Account createAccount(@RequestBody @Valid AccountUserRequest request) {
////
////    }
//
//    @DeleteMapping("/{id}")
//    public void deleteAccount(@PathVariable Integer id ) {
//    	Account acount=accountRepository.getById(id);
//    	accountRepository.delete(acount);
//    }
//    @PostMapping("/find")
//    public boolean findAccount(@RequestParam String username,
//    								   @RequestParam String password ) {
//
//    	return accountService.findAccount(username, password);
//    }
//}
