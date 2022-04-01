package com.currentaccountapi.customer.model;
import com.currentaccountapi.account.model.Account;
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
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long customerId;
    private String name;
    private String surname;
    private Double balance;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    @JsonProperty("accounts")
    private List<Account> accounts;

    public Customer(String name, String surname, Double balance, List <Account> accounts) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Double.compare(customer.balance, balance) == 0 && Objects.equals(customerId, customer.customerId) && Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) && Objects.equals(accounts, customer.accounts);
    }

}
