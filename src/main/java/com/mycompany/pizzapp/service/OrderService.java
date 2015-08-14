/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.service;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Pizza;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author margarita
 */
public interface OrderService {


    Order placeNewOrder(Customer customer, Integer... pizzasID);

    Order placeNewOrder(Customer customer, Map<Pizza, Integer> pizzasInOrder);

    Double calculateTotalPrice(Order order);

    Order getOrderById(Integer id);

    Order placeNewOrder(String jsonOrder);
}
