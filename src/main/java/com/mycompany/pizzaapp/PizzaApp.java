/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzaapp;

import java.lang.reflect.InvocationTargetException;

import com.mycompany.infrastructure.ApplicationContext;
import com.mycompany.infrastructure.Config;
import com.mycompany.infrastructure.JavaConfig;
import com.mycompany.pizzaapp.infrastructure.JavaConfigApplicationContext;
import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.repository.PizzaRepository;
import com.mycompany.pizzapp.service.OrderService;

public class PizzaApp {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
    	Customer customer = new Customer(1, "Margo");        
        
        //ObjectFactory objectFactory = ObjectFactory.getInstance(); 
        
        //OrderService orderService = new SimpleOrderService(null, null);
     
        Config config = new JavaConfig();
    	
    	ApplicationContext context = new JavaConfigApplicationContext(config);
    	OrderService orderService = (OrderService) context.getBean("orderService");
    	PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
    	
    	System.out.println(orderService);
    	System.out.println(pizzaRepository);
    	
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        
        System.out.println(order);
    }
    
}
