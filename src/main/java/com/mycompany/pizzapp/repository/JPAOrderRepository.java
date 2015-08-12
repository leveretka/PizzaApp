package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository{

    @PersistenceContext(name="HibernatePostgreSql")
    private EntityManager em;


    @Transactional
    @Override
    public void saveOrder(Order newOrder) {
        em.persist(newOrder);
    }
}
