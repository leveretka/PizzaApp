package com.mycompany.pizzapp;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.repository.PizzaRepository;
import com.mycompany.pizzapp.service.OrderService;

public class SpringPizzaApp {
	
	public static void main(String[] args) {
		
        ConfigurableApplicationContext repositoryContext 
        	= new ClassPathXmlApplicationContext("repositoryContext.xml");

        ConfigurableApplicationContext appContext
        	= new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}
        	, repositoryContext);

        PizzaRepository pizzaRepository 
        	= (PizzaRepository) appContext.getBean("pizzaRepository");
        
        System.out.println(pizzaRepository);

        String[] beans = appContext.getBeanDefinitionNames();

        OrderService orderService = (OrderService) appContext.getBean("orderService");
		
		Customer customer = new Customer(1, "Margo");        
        
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        
        System.out.println(order);
		
		appContext.close();
	}

}
