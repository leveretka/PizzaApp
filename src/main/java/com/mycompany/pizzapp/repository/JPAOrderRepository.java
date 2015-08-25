package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.AccumulativeCard;
import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Pizza;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository{

    @PersistenceContext(name="HibernatePostgreSql")
    private EntityManager em;


    @Transactional
    @Override
    public void saveOrder(Order newOrder) {
        newOrder.calcTotalCost();
        em.merge(newOrder.getCustomer());
        em.persist(newOrder);

        AccumulativeCard card = newOrder.getCustomer().getAccumulativeCard();

        card.add(newOrder.getTotalCost());
        em.merge(card);

    }

    @Override
    public Order getOrderByID(Integer id) {
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.id = :id", Order.class);
        query.setParameter("id", id);
        Order order = query.getSingleResult();
        return order;

    }

    @Override
    public List<Order> getAllOrders() {
        TypedQuery<Order> query = em.createQuery("select o from Order o", Order.class);
        List<Order> orders = query.getResultList();
        return orders;
    }

    @Override
    public List<Order> getAllCustomerOrders(Customer customer) {
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.customer = :cust", Order.class);
        query.setParameter("cust", customer);
        List<Order> orders = query.getResultList();
        return orders;
    }


}
