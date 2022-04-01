package com.currentaccountapi.transaction.model;

import com.currentaccountapi.account.model.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name="account_id")
    @JsonBackReference
    private Account account;
    private Long customerId;
    private LocalDateTime transactionDate;
    private String transactionStatus;
    private Double amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(account, that.account) && Objects.equals(customerId, that.customerId) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(transactionStatus, that.transactionStatus) && Objects.equals(amount, that.amount);
    }

    public enum TransactionType {
        INITIAL_CREDIT, PENDING, REJECTED
    }
}
