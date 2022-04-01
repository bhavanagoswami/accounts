package com.currentaccountapi.account.service;

import com.currentaccountapi.account.model.Account;
import com.currentaccountapi.account.model.AccountCreationRequest;
import com.currentaccountapi.customer.model.Customer;
import com.currentaccountapi.account.repository.AccountRepository;
import com.currentaccountapi.customer.service.CustomerService;
import com.currentaccountapi.shared.exception.CustomerNotFoundException;
import com.currentaccountapi.transaction.model.Transaction;
import com.currentaccountapi.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    private CustomerService customerService;

    private TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @Override
    public List<Account> getAllAccount(Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if(Objects.isNull(customer))
            return null;
       return customer.getAccounts();
    }

    @Override
    public ResponseEntity<Object> createAccount(AccountCreationRequest accountCreationRequest) throws Exception {
        Customer customer = customerService.getCustomer(accountCreationRequest.getCustomerId());
        if(Objects.isNull(customer)) {
            log.error("Customer Not found !! Please enter correct customer id.");
            throw new CustomerNotFoundException();
        }
        Account account = new Account();
        account.setAccountName(accountCreationRequest.getAccountName());
        account.setCustomerId(accountCreationRequest.getCustomerId());
        account.setInitialCredit(accountCreationRequest.getInitialCredit());
        Account newAccount = accountRepository.save(account);
        if(Objects.nonNull(accountCreationRequest.getInitialCredit()) && accountCreationRequest.getInitialCredit() > 0){
            Transaction transaction = new Transaction();
            transaction.setAccount(newAccount);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setTransactionStatus(Transaction.TransactionType.INITIAL_CREDIT.name());
            transaction.setAmount(accountCreationRequest.getInitialCredit());
            transaction.setCustomerId(newAccount.getCustomerId());
            transactionService.createTransaction(transaction);

        }
        log.info("Account created with Id {}",newAccount.getAccountId());
        return new ResponseEntity<>("Account is created successfully, Account ID is:"+newAccount.getAccountId(), HttpStatus.OK);
    }

    @Override
    public Account getAccountById(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.orElse(null);
    }
}
