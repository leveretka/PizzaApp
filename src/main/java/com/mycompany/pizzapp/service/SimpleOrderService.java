/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.domain.TotalOrderCostCalculator;
import com.mycompany.pizzapp.infrastructure.Benchmark;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.repository.OrderRepository;
import com.mycompany.pizzapp.repository.PizzaRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
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
    private TotalOrderCostCalculator totalOrderCostCalculator;

    @Autowired
    public SimpleOrderService(PizzaRepository pizzaRepository, 
    		OrderRepository orderRepository) throws InstantiationException, IllegalAccessException {
        
    	this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
        
    }

    public SimpleOrderService() {
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
    	
        Order newOrder = new Order(customer, pizzas);
        newOrder.calcTotalCost();
       
        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    @Override
    public Order placeNewOrder(Customer customer, Map<Pizza, Integer> pizzasInOrder) {
        Order newOrder = new Order(customer, pizzasInOrder);

        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    @Override
    public Double calculateTotalPrice(Order order) {
        return totalOrderCostCalculator.calculateTotalOrderPrice(order.getPizzas());
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.getOrderByID(id);
    }

    @Override
    public Order placeNewOrder(String jsonOrder) {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = null;
        Customer customer = null;
        Integer[] pizzas = null;

        try {
            root = mapper.readTree(jsonOrder);

            JsonNode customerNode = root.get("Customer");
            String name = customerNode.get("name").asText();
            customer = new Customer(name);

            JsonNode pizzaIdsNode = root.get("Pizzas");
            if(pizzaIdsNode.isArray()) {
                pizzas = new Integer[pizzaIdsNode.size()];
                int i = 0;
                for (final JsonNode idNode : pizzaIdsNode) {
                    pizzas[i] = idNode.asInt();
                    i++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return placeNewOrder(customer, pizzas);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public List<Order> getAllCustomerOrders(Customer customer) {
        return orderRepository.getAllCustomerOrders(customer);
    }

    @Lookup(value="order")
    protected Order getNewOrder() {
        return null;
    }



}