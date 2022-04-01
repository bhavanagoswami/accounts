package com.currentaccountapi.customer.service;

import com.currentaccountapi.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer getCustomer(Long customerId);

    public List<Customer> getAllCustomers();

    public Long save(Customer customer);

    public void deleteAll();
}
