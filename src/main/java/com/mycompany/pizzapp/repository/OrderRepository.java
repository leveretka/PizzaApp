/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.Order;

/**
 *
 * @author margarita
 */
public interface OrderRepository {

    void saveOrder(Order newOrder);
    
}
