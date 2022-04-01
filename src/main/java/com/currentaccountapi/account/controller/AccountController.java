package com.currentaccountapi.account.controller;

import com.currentaccountapi.account.model.Account;
import com.currentaccountapi.account.model.AccountCreationRequest;
import com.currentaccountapi.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;


    @PostMapping("/account")
    public ResponseEntity<Object> createAccount(@RequestBody AccountCreationRequest accountCreationRequest) throws Exception {
        return accountService.createAccount(accountCreationRequest);
    }

    @GetMapping("/accounts/customer/{customer_id}")
    public List<Account> getAllAccountByCustomerId(@PathVariable Long customer_id){
        return accountService.getAllAccount(customer_id);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountByAccountId(@PathVariable Long id){
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }
}
