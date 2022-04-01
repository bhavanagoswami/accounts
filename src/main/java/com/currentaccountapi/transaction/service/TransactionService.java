package com.currentaccountapi.transaction.service;

import com.currentaccountapi.transaction.model.Transaction;

public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);
}
