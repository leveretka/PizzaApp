/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.service;

import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.infrastructure.Benchmark;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.repository.OrderRepository;
import com.mycompany.pizzapp.repository.PizzaRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

/**
 *
 * @author margarita
 */
@Service
public class SimpleOrderService implements OrderService {
    
    //private ObjectFactory objectFactory = ObjectFactory.getInstance();
    
    private OrderRepository orderRepository;
    private PizzaRepository pizzaRepository;

    @Autowired
    public SimpleOrderService(PizzaRepository pizzaRepository, 
    		OrderRepository orderRepository) throws InstantiationException, IllegalAccessException {
        
    	this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
        
    }
    
    @Autowired
    public void setMethod(PizzaRepository pizzaRepository) {
    	System.out.println("PizzaRepo - testMethod");
    	this.pizzaRepository = pizzaRepository;
    }
    
    @Benchmark
    @Override
    public Order placeNewOrder(Customer customer, Integer ... pizzasID) {

    	Map<Pizza, Integer> pizzas = new HashMap<>();
    	for (Integer id: pizzasID) {
    		Pizza p = pizzaRepository.getPizzaByID(id);
    		if (pizzas.containsKey(p)) {
    			int count = pizzas.get(p);
    			count++;
    			pizzas.remove(p);
    			pizzas.put(p, count);
    		} else {
    			pizzas.put(p, 1);
    		}
    			
    	}
    	
//        List<Pizza> pizzas = new ArrayList<>();
//       
//        for(Integer id : pizzasID){
//            pizzas.add(pizzaRepository.getPizzaByID(id));  // get Pizza from predifined in-memory list
//        }
        Order newOrder = new Order(customer, pizzas);
       
        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }
    
    @Lookup(value="order")
    protected Order getNewOrder() {
        return null;
    }



}