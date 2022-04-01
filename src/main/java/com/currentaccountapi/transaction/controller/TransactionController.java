package com.currentaccountapi.transaction.controller;

import com.currentaccountapi.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionController {

    @Autowired
    private TransactionService transactionService;

}
