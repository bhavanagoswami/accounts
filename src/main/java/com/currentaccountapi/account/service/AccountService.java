package com.currentaccountapi.account.service;

import com.currentaccountapi.account.model.Account;
import com.currentaccountapi.account.model.AccountCreationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    public List<Account> getAllAccount(Long customerId);

    ResponseEntity<Object> createAccount(AccountCreationRequest accountCreationRequest) throws Exception;

    public Account getAccountById(Long accountId);
}
