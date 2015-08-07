package com.mycompany.pizzapp.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.domain.PizzaType;
import com.mycompany.pizzapp.repository.template.ITRepositoryTestsTemplate;

public class JPAPizzaRepositoryTest extends ITRepositoryTestsTemplate {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Test
	public void testSomeMethod() {
		Pizza pizza = new Pizza();
		pizza.setName("Meat");
		pizza.setType(PizzaType.MEAT);
		pizza.setPrice(123.1);
		
		Integer id = pizzaRepository.save(pizza);
		System.out.println(id);
		
	}
}
