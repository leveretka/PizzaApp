package com.mycompany.pizzapp.domain;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

import java.util.Iterator;

@Component
public class TotalOrderCostCalculator {

	private static final TotalOrderCostCalculator instance = new TotalOrderCostCalculator();

	private TotalOrderCostCalculator(){}

	public static TotalOrderCostCalculator getInstance() {
		return instance;
	}

	public double calculateTotalOrderPrice(Map<Pizza, Integer> pizzas) {

		if (pizzas == null || pizzas.size() == 0) {
			throw new IllegalArgumentException();			
		}
		
		Iterator<Integer> ammount = pizzas.values().iterator();
		Iterator<Pizza> pizzaInOrder = pizzas.keySet().iterator();
		
		double total = 0;
		int totalQnt = 0;
		double maxPrice = 0;
		
		while (ammount.hasNext()) {

			Integer amm = ammount.next();
			if (amm <= 0) {
				throw new NumberFormatException();				
			}

			totalQnt += amm;
			if (totalQnt > 10) {
				throw new IllegalArgumentException();
			}
			Pizza pizza = pizzaInOrder.next();
			if (pizza.getPrice() > maxPrice) {
				maxPrice = pizza.getPrice();
			}
			total += amm * pizza.getPrice();
			
		}

		if (totalQnt > 4) {
			total -=  0.3 * maxPrice;
		}
		return total;
				
	}
	public double calculateTotalOrderPrice(Map<Pizza, Integer> pizzas, double accumulativeSum) {

		if (pizzas == null || pizzas.size() == 0) {
			throw new IllegalArgumentException();
		}

		Iterator<Integer> amount = pizzas.values().iterator();
		Iterator<Pizza> pizzaInOrder = pizzas.keySet().iterator();

		double total = 0;
		int totalQnt = 0;

		while (amount.hasNext()) {

			Integer amm = amount.next();
			if (amm <= 0) {
				throw new NumberFormatException();
			}

			totalQnt += amm;
			if (totalQnt > 10) {
				throw new IllegalArgumentException();
			}

			Pizza pizza = pizzaInOrder.next();

			total += amm * pizza.getPrice();

		}

		if (accumulativeSum < 1000) {
			total *= 0.99;
		} else if (accumulativeSum < 2000) {
			total *= 0.97;
		} else if (accumulativeSum < 5000) {
			total *= 0.95;
		} else if (accumulativeSum < 10000) {
			total *= 0.93;
		} else if (accumulativeSum < 20000) {
			total *= 0.9;
		} else {
			total *= 0.8;
		}

		return total;

	}
}
