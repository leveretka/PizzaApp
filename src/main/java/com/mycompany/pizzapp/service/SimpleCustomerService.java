package com.mycompany.pizzapp.service;

import com.mycompany.pizzapp.domain.AccumulativeCard;
import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.User;
import com.mycompany.pizzapp.domain.UserRole;
import com.mycompany.pizzapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by margarita on 22.08.15.
 */
@Service
public class SimpleCustomerService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addNewCustomer(String fullName, String login, String password) {

        Customer customer = new Customer(fullName);
        User user = new User(login, password);

        AccumulativeCard card = new AccumulativeCard();
        card.setTotalSum(0);
        card.setCustomer(customer);


        customer.setAccumulativeCard(card);
        customer.setUser(user);

        UserRole ur = new UserRole(user, "ROLE_USER");

        customerRepository.saveCustomer(customer);
        return customer;
    }

    public SimpleCustomerService() {
    }

    @Override
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    @Override
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        return customerRepository.getCustomerByUserName(userName);
    }
}
