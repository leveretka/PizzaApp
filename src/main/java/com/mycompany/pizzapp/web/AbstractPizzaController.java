package com.mycompany.pizzapp.web;

import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.service.OrderService;
import com.mycompany.pizzapp.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

/**
 * Created by margarita on 12.08.15.
 */
public abstract class AbstractPizzaController {

    @Autowired
    protected PizzaService pizzaService;

    @Autowired
    protected OrderService orderService;

    protected Pizza getPizzabyId(Integer id) {
        if (id<=0) throw new IllegalArgumentException("ID<0");
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null)
            throw new NotFoundPizzaException("Pizza id" + id + " not found" );
        return pizza;

    }

    @InitBinder
    protected void pizzaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {

                    @Override
                    public void setAsText(String pizzasId) {
                        Pizza pizza = null;
                        if (pizzasId != null && !pizzasId.trim().isEmpty()) {
                            Integer id = Integer.parseInt(pizzasId);
                            pizza = getPizzabyId(id);
                        }
                        setValue(pizza);
                    }
                });
    }

    protected Order getOrderbyId(Integer id) {
        if (id<=0) throw new IllegalArgumentException("ID<0");
        Order order = orderService.getOrderById(id);
        if (order == null)
            throw new NotFoundOrderException("Order id" + id + " not found" );
        return order;

    }

//    @InitBinder
//    protected void orderBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Order.class,
//                new PropertyEditorSupport() {
//
//                    @Override
//                    public void setAsText(String orderId) {
//                        Order order = null;
//                        if (orderId != null && !orderId.trim().isEmpty()) {
//                            Integer id = Integer.parseInt(orderId);
//                            order = getOrderbyId(id);
//                        }
//                        setValue(order);
//                    }
//                });
//    }

}
