package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.User;
import com.mycompany.pizzapp.domain.UserRole;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by margarita on 21.08.15.
 */
public interface CustomerRepository {

    @Transactional
    void saveCustomer(Customer newCustomer);

    @Transactional
    void saveUserRole(UserRole newUserRole);

    Customer getCustomerByID(Integer id);

    List<Customer> getAllCustomers();

    User getUserByID(Integer id);

    List<User> getAllUsers();

    Customer getCustomerByUserName(String userName);
}
