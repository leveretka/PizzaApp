package com.mycompany.pizzapp.domain;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.mycompany.pizzapp.service.OrderService;
import com.mycompany.pizzapp.service.PizzaService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

public class TotalOrderCostCalculatorTest {

	private static double DELTA = 0.001;

	@Mock
	private PizzaService pizzaService;

	@Before
	public void setUp()	throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	private final TotalOrderCostCalculator orderCostCalculator = new TotalOrderCostCalculator();
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPricePizzaUnderLimitThrownException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		orderCostCalculator.calculateTotalOrderPrice(pizzas);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPricePizzaAboveLimitThrownException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 11);
		orderCostCalculator.calculateTotalOrderPrice(pizzas);
	}
	
	@Test
	public void testCalculateTotalOrderPriceOrderWithOnePizza() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 1);
		double expectedPrice = 22.3;
		
		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas);
		//assertEquals(expectedPrice, price);
		assertEquals(expectedPrice, price, DELTA);
		
	}
	
	@Test(expected = NumberFormatException.class)
	public void testCalculateTotalOrderPriceNegativeAmmountThrownException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), -1);
		
		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas);
		
	}

	@Test(expected = NumberFormatException.class)
	public void testCalculateTotalOrderPriceZeroAmmountThrownException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 0);
		
		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas);
		
	}
	
	@Test
	public void testCalculateTotalOrderPriceOrderWithManyPizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 3);
		double expectedPrice = 22.3 * 3;
		
		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas);
		
		assertEquals(expectedPrice, price, DELTA);
		
	}

	@Test
	public void testCalculateTotalOrderPriceOrderWithManyDifferentPizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(2, "Margo1", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(3, "Margo2", 22.3, PizzaType.SEA), 1);
		double expectedPrice = 22.3 * 3;
		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas);
		
		assertEquals(expectedPrice, price, DELTA);
		
	}

	@Test
	public void testCalculateTotalOrderPriceOrderWithMoreThanFourPizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1, "Margo", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(2, "Margo1", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(3, "Margo2", 100.0, PizzaType.SEA), 1);
		pizzas.put(new Pizza(4, "Margo3", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(5, "Margo4", 22.3, PizzaType.SEA), 1);
		pizzas.put(new Pizza(6, "Margo5", 22.3, PizzaType.SEA), 1);
		double expectedPrice = 22.3 * 5 + 70;
		
		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas);
		
		assertEquals(expectedPrice, price, DELTA);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceWithAccumulatedNegativeSum() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		when(pizzaService.getPizzaById(anyInt())).thenReturn(new Pizza(0, "Margo", 50.0, PizzaType.MEAT));
		for (int i = 0; i < 5; ++i) {
			pizzas.put(pizzaService.getPizzaById(i), i);
		}
		orderCostCalculator.calculateTotalOrderPrice(pizzas, -100);
	}

	@Test
	public void testCalculateTotalOrderPriceWithAccumulatedLess1000Sum() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		when(pizzaService.getPizzaById(anyInt())).thenReturn(new Pizza(0, "Margo", 50.0, PizzaType.MEAT));

		pizzas.put(pizzaService.getPizzaById(0), 5);

		double expectedPrice = 50 * 5 * 0.99;

		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas, 999);

		assertEquals(expectedPrice, price, DELTA);

	}

	@Test
	public void testCalculateTotalOrderPriceWithAccumulatedLess2000Sum() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		when(pizzaService.getPizzaById(anyInt())).thenReturn(new Pizza(0, "Margo", 50.0, PizzaType.MEAT));

		pizzas.put(pizzaService.getPizzaById(0), 5);

		double expectedPrice = 50 * 5 * 0.97;

		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas, 1999);

		assertEquals(expectedPrice, price, DELTA);

	}

	@Test
	public void testCalculateTotalOrderPriceWithAccumulatedLess5000Sum() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		when(pizzaService.getPizzaById(anyInt())).thenReturn(new Pizza(0, "Margo", 50.0, PizzaType.MEAT));

		pizzas.put(pizzaService.getPizzaById(0), 5);

		double expectedPrice = 50 * 5 * 0.95;

		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas, 4999);

		assertEquals(expectedPrice, price, DELTA);

	}

	@Test
	public void testCalculateTotalOrderPriceWithAccumulatedLess10000Sum() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		when(pizzaService.getPizzaById(anyInt())).thenReturn(new Pizza(0, "Margo", 50.0, PizzaType.MEAT));

		pizzas.put(pizzaService.getPizzaById(0), 5);

		double expectedPrice = 50 * 5 * 0.93;

		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas, 9999);

		assertEquals(expectedPrice, price, DELTA);

	}

	@Test
	public void testCalculateTotalOrderPriceWithAccumulatedLess20000Sum() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		when(pizzaService.getPizzaById(anyInt())).thenReturn(new Pizza(0, "Margo", 50.0, PizzaType.MEAT));

		pizzas.put(pizzaService.getPizzaById(0), 5);

		double expectedPrice = 50 * 5 * 0.90;

		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas, 19999);

		assertEquals(expectedPrice, price, DELTA);

	}

	@Test
	public void testCalculateTotalOrderPriceWithAccumulated20000Sum() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		when(pizzaService.getPizzaById(anyInt())).thenReturn(new Pizza(0, "Margo", 50.0, PizzaType.MEAT));

		pizzas.put(pizzaService.getPizzaById(0), 5);

		double expectedPrice = 50 * 5 * 0.80;

		double price = orderCostCalculator.calculateTotalOrderPrice(pizzas, 20000);

		assertEquals(expectedPrice, price, DELTA);

	}
}
