package com.currentaccountapi.shared;

import com.currentaccountapi.customer.model.Customer;
import com.currentaccountapi.customer.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader {

    @Bean
    public CommandLineRunner commandlineRunner(CustomerRepository customerRepository) throws Exception {

        return args -> {
            customerRepository.deleteAllInBatch();
            List<Customer> customers = new ArrayList<>();
            customers.add(createCustomer("One", "TT",100d));
            customers.add(createCustomer("Mr.", "Frost",0d));
            customers.add(createCustomer("Santa", "Clause",null));
            customers.add(createCustomer("Peter", "Pan",22d));
            customers.add(createCustomer("Cinder", "ella",44d));
            customers.add(createCustomer("What", "ever",3000d));
            customerRepository.saveAll(customers);
        };
    }

    private Customer createCustomer(String name, String surname, Double balance) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setBalance(balance);
        return customer;
    }
}
