package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Pizza;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    @Override
    public Order getOrderByID(Integer id) {
        TypedQuery<Order> query = em.createQuery("select o from `order` o where o.id = :id", Order.class);
        query.setParameter("id", id);
        Order order = query.getSingleResult();
        return order;

    }


}
