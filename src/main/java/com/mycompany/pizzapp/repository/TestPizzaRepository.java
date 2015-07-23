/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.Pizza;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author margarita
 */
public class TestPizzaRepository implements PizzaRepository {
    
    private List<Pizza> pizzas = Arrays.asList(new Pizza(1, "Sea", 22.3, Pizza.Type.SEA), new Pizza(2, "Meat", 20.3, Pizza.Type.MEAT), new Pizza(3, "Vegeterian", 18.3, Pizza.Type.VEGETERIAN));

    @Override
    public Pizza getPizzaByID(Integer id) {
        for (Pizza p : pizzas) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
}
