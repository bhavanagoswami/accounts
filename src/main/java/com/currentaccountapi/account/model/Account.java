package com.currentaccountapi.account.model;


import com.currentaccountapi.transaction.model.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;

    @Column(name = "account_name")
    @JsonProperty("accountName")
    private String accountName;

    @Column(name = "transactions")
    @OneToMany(mappedBy="account", fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    @Column(name = "initial_credit")
    private Double initialCredit;

    @Column(name = "customer_id")
    @JsonProperty("customerId")
    private Long customerId = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.initialCredit, initialCredit) == 0 && Objects.equals(accountId, account.accountId) && Objects.equals(accountName, account.accountName) && Objects.equals(transactions, account.transactions) && Objects.equals(customerId, account.customerId);
    }

}

