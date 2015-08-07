/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.repository;

import com.mycompany.pizzapp.domain.Pizza;
import java.util.List;

/**
 *
 * @author margarita
 */
public abstract class TestPizzaRepository implements PizzaRepository {
    
    private List<Pizza> pizzas;
    
    public void setPizzas(List<Pizza> pizzas) {
    	this.pizzas = pizzas;
    }
    
    public void init() {
//    	pizzas = Arrays.asList(
//        		new Pizza(1, "Sea", 22.3, PizzaType.SEA),
//        		new Pizza(2, "Meat", 20.3, PizzaType.MEAT),
//        		new Pizza(3, "Veg", 18.3, PizzaType.VEGETERIAN)
//        );
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        for (Pizza p : pizzas) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

	@Override
	public List<Pizza> getAllPizzas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Pizza p) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
