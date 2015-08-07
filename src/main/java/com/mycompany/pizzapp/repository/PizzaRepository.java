/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.repository;

import java.util.List;

import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.domain.PizzaType;

/**
 *
 * @author margarita
 */
public interface PizzaRepository {

    Pizza getPizzaByID(Integer id);
    List<Pizza> getAllPizzas();
    List<Pizza> getAllPizzasWithType(PizzaType type);    
    public Integer save(Pizza p);
}
