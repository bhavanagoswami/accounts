package com.currentaccountapi.customer.controller;

import com.currentaccountapi.customer.model.Customer;
import com.currentaccountapi.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/all")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity getCustomer(@PathVariable long customerId) throws Exception {

        Customer customer = customerService.getCustomer(customerId);
        if (Objects.isNull(customer)) {
            throw new Exception("Customer Not Found !!");
        }
        return ResponseEntity.ok().body(customerService.getCustomer(customerId));
    }

    @PostMapping("/customer")
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok().body(customerService.save(customer));
    }
}
