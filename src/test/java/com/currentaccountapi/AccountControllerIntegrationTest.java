package com.currentaccountapi;

import com.currentaccountapi.account.model.Account;
import com.currentaccountapi.account.model.AccountCreationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test_getCustomer() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange("/customer/all",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    void addAccount() {
        AccountCreationRequest accountCreationRequest = new AccountCreationRequest();
        accountCreationRequest.setCustomerId(1L);
        accountCreationRequest.setAccountName("Account 1");
        accountCreationRequest.setInitialCredit(100d);

        ResponseEntity<String> postResponse = restTemplate.postForEntity("/account", accountCreationRequest, String.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    void getAccount() {
        Long id = 101L;
        ResponseEntity<Account> response = restTemplate.getForEntity("/account/{id}", Account.class, id);
        assertNotNull(response.getStatusCode());
    }

}
