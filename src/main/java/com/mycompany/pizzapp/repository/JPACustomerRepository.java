package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.User;
import com.mycompany.pizzapp.domain.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by margarita on 21.08.15.
 */
@Repository("customerRepository")
public class JPACustomerRepository implements CustomerRepository {

    @PersistenceContext(name="HibernatePostgreSql")
    private EntityManager em;

    @Override
    @Transactional
    public void saveCustomer(Customer newCustomer) {
        em.persist(newCustomer);
        User user = newCustomer.getUser();
        saveUserRole(new UserRole(user, "ROLE_USER"));

    }

    @Override
    @Transactional
    public void saveUserRole(UserRole newUserRole) {
        em.persist(newUserRole);
    }

    @Override
    public Customer getCustomerByID(Integer id) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.id = :id", Customer.class);
        query.setParameter("id", id);
        Customer customer = query.getSingleResult();
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        List<Customer> customers = query.getResultList();
        return customers;
    }

    @Override
    public User getUserByID(Integer id) {
        TypedQuery<User> query = em.createQuery("select u from User c where u.id = :id", User.class);
        query.setParameter("id", id);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.user.name=:name", Customer.class);
        query.setParameter("name", userName);
        List<Customer> customers = query.getResultList();
        if (customers.size() != 1) {
            return null;
        }
        return customers.get(0);
    }

}
