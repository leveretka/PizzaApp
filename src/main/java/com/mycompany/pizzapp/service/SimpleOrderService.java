/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.service;

import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.repository.OrderRepository;
import com.mycompany.pizzapp.repository.PizzaRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author margarita
 */
public class SimpleOrderService implements OrderService {
    
    //private ObjectFactory objectFactory = ObjectFactory.getInstance();
    
    private OrderRepository orderRepository;
    private PizzaRepository pizzaRepository;

    public SimpleOrderService(PizzaRepository pizzaRepository, 
    		OrderRepository orderRepository) throws InstantiationException, IllegalAccessException {
        
    	this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
        
    }
    
    
    @Override
    public Order placeNewOrder(Customer customer, Integer ... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();
       
        for(Integer id : pizzasID){
            pizzas.add(pizzaRepository.getPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = new Order(customer, pizzas);
       
        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }


}