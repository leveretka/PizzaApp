/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.service;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.Order;

/**
 *
 * @author margarita
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, Integer... pizzasID);
    
}
