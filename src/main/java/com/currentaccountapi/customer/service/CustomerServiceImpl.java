package com.currentaccountapi.customer.service;

import com.currentaccountapi.customer.model.Customer;
import com.currentaccountapi.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Long save(Customer customer) {
        return customerRepository.save(customer).getCustomerId();
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }
}
