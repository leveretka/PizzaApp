/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.service;

import java.util.List;

import com.mycompany.pizzapp.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface PizzaService {
    List<Pizza> getAllPizzas();
    Integer addPizza(Pizza pizza);
    Pizza getPizzaById(Integer id);
}
