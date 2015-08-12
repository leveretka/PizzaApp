/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.Order;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 *
 * @author margarita
 */
public class TestOrderRepository implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void saveOrder(Order newOrder) {
        //newOrder.setId(orders.size() + 1);
        orders.add(newOrder);
    }
    
}
