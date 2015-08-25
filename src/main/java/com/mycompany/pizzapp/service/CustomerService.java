package com.mycompany.pizzapp.service;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.repository.CustomerRepository;

/**
 * Created by margarita on 23.08.15.
 */
public interface CustomerService {
    Customer addNewCustomer(String fullName, String login, String password);

    CustomerRepository getCustomerRepository();

    void setCustomerRepository(CustomerRepository customerRepository);

    Customer getCustomerByUserName(String userName);
}
