package com.mycompany.pizzapp.service;

import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.repository.JPAOrderRepository;
import com.mycompany.pizzapp.repository.JPAPizzaRepository;
import org.junit.Test;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertNotNull;

/**
 * Created by margarita on 13.08.15.
 */
@Component
public class SimpleOrderServiceTest {



    @Test
    public void placeNewOrderTest() {
        OrderService orderService = null;
        try {
            orderService = new SimpleOrderService(new JPAPizzaRepository(), new JPAOrderRepository());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String json = "{\"Customer\":{\"name\":\"Love\"},\"Pizzas\":[1,1,1,2,3]}";
        Order order = orderService.placeNewOrder(json);
        assertNotNull(order);
    }

}
