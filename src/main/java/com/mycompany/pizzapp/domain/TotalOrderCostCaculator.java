package com.mycompany.pizzapp.domain;

import java.util.Map;
import java.util.Set;

import java.util.Iterator;

public class TotalOrderCostCaculator {
	public double calculateTotalOrderPrice(Map<Pizza, Integer> pizzas) {

		if (pizzas == null || pizzas.size() == 0) {
			throw new IllegalArgumentException();			
		}
		
		Iterator<Integer> ammount = pizzas.values().iterator();
		Iterator<Pizza> pizzaInOrder = pizzas.keySet().iterator();
		
		double total = 0;
		int totalQnt = 0;
		double maxPrice = 0;
		
		for (int i = 0; ammount.hasNext();) {

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
		System.out.println("Total = " + total);
		System.out.println("MaxPrice = " + maxPrice);
		
		if (totalQnt > 4) {
			total -=  0.3 * maxPrice;
		}
		return total;
				
	}
}
