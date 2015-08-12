package com.mycompany.pizzapp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.domain.PizzaType;
import com.mycompany.pizzapp.repository.PizzaRepository;
import com.mycompany.pizzapp.service.OrderService;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class SpringPizzaApp {
	
	public static void main(String[] args) {
		
        ConfigurableApplicationContext repositoryContext 
        	= new ClassPathXmlApplicationContext("repositoryContext.xml");

        ConfigurableApplicationContext appContext
        	= new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}
        	, repositoryContext);

        PizzaRepository pizzaRepository 
        	= (PizzaRepository) appContext.getBean("pizzaRepository");

        OrderService orderService = (OrderService) appContext.getBean(OrderService.class);

		Customer customer = new Customer("Margo");

//        Order order = orderService.placeNewOrder(customer, 2, 1, 1, 1);
        //System.out.println(order);

		appContext.close();
	}

}
