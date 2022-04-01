package com.currentaccountapi.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationRequest {
    private Long customerId;

    private String accountName;

    private Double initialCredit;
}
