package com.mycompany.pizzapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.repository.PizzaRepository;

import java.util.List;

@Service
public class SimplePizzaService implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Override
    public List<Pizza> getAllPizzas() {
    	List<Pizza> pizzas = pizzaRepository.getAllPizzas();
        return pizzas;
    }

    @Override
    public Integer addPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza getPizzaById(Integer id) {
    	return pizzaRepository.getPizzaByID(id);
    }
    
}
