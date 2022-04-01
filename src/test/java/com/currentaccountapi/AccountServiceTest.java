package com.currentaccountapi;

import com.currentaccountapi.account.model.Account;
import com.currentaccountapi.account.model.AccountCreationRequest;
import com.currentaccountapi.account.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    AccountService accountService;


    @Test
    public void when_initialCredit_is_null_then_no_transaction_in_account() throws Exception {
       AccountCreationRequest accountCreationRequest = new AccountCreationRequest(1l, "account1", null);
       ResponseEntity<Object> entity = accountService.createAccount(accountCreationRequest);
       Assertions.assertEquals(entity.getStatusCode().is2xxSuccessful(), true);
       String responseBody = entity.getBody().toString();
       String[] stringArray = responseBody.split(":");
       Account accountNew = accountService.getAccountById(Long.parseLong(stringArray[1]));
       Assertions.assertTrue(accountNew.getTransactions().isEmpty());
    }

    @Test
    public void when_initialCredit_is_not_null_then_transaction_added_to_account() throws Exception {
        AccountCreationRequest accountCreationRequest = new AccountCreationRequest(1l, "account1", 1000d);
        ResponseEntity<Object> entity = accountService.createAccount(accountCreationRequest);
        Assertions.assertEquals(entity.getStatusCode().is2xxSuccessful(), true);
        String responseBody = entity.getBody().toString();
        String[] stringArray = responseBody.split(":");
        Account accountNew = accountService.getAccountById(Long.parseLong(stringArray[1]));
        Assertions.assertNotNull(accountNew.getTransactions());
    }
}
