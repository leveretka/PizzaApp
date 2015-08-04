package com.mycompany.pizzapp.domain;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class TotalOrderCostCalculatorTest {

	private static double DELTA = 0.001;
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPricePizzaUnderLimitThrownException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		TotalOrderCostCaculator orderCostCaculator = new TotalOrderCostCaculator();
		orderCostCaculator.calculateTotalOrderPrice(pizzas);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPricePizzaAboveLimitThrownException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 11);
		TotalOrderCostCaculator orderCostCaculator = new TotalOrderCostCaculator();
		orderCostCaculator.calculateTotalOrderPrice(pizzas);
	}
	
	@Test
	public void testCalculateTotalOrderPriceOrderWithOnePizza() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 1);
		double expectedPrice = 22.3;
		
		TotalOrderCostCaculator orderCostCaculator = new TotalOrderCostCaculator();
		double price = orderCostCaculator.calculateTotalOrderPrice(pizzas);	
		//assertEquals(expectedPrice, price);
		assertEquals(expectedPrice, price, DELTA);
		
	}
	
	@Test(expected = NumberFormatException.class)
	public void testCalculateTotalOrderPriceNegativeAmmountThrownException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), -1);
		
		TotalOrderCostCaculator orderCostCaculator = new TotalOrderCostCaculator();
		double price = orderCostCaculator.calculateTotalOrderPrice(pizzas);	
		
	}

	@Test(expected = NumberFormatException.class)
	public void testCalculateTotalOrderPriceZeroAmmountThrownException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 0);
		
		TotalOrderCostCaculator orderCostCaculator = new TotalOrderCostCaculator();
		double price = orderCostCaculator.calculateTotalOrderPrice(pizzas);	
		
	}
	
	@Test
	public void testCalculateTotalOrderPriceOrderWithManyPizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 3);
		double expectedPrice = 22.3 * 3;
		
		TotalOrderCostCaculator orderCostCaculator = new TotalOrderCostCaculator();
		double price = orderCostCaculator.calculateTotalOrderPrice(pizzas);	
		
		assertEquals(expectedPrice, price, DELTA);
		
	}

	@Test
	public void testCalculateTotalOrderPriceOrderWithManyDifferentPizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(2, "Margo1", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(3, "Margo2", 22.3, PizzaType.SEA), 1);
		double expectedPrice = 22.3 * 3;
		
		TotalOrderCostCaculator orderCostCaculator = new TotalOrderCostCaculator();
		double price = orderCostCaculator.calculateTotalOrderPrice(pizzas);	
		
		assertEquals(expectedPrice, price, DELTA);
		
	}

	@Test
	public void testCalculateTotalOrderPriceOrderWithMoreThanFourPizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(2, "Margo1", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(3, "Margo2", 100.0, PizzaType.SEA), 1);
		pizzas.put(new Pizza(4, "Margo", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(5, "Margo1", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(6, "Margo2", 22.3, PizzaType.SEA), 1);
		double expectedPrice = 22.3 * 5 + 70;
		
		TotalOrderCostCaculator orderCostCaculator = new TotalOrderCostCaculator();
		double price = orderCostCaculator.calculateTotalOrderPrice(pizzas);	
		
		assertEquals(expectedPrice, price, DELTA);
		
	}

}
