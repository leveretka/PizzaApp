/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.domain;

import java.util.Map;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author margarita
 */

@Component(value="order")
@Scope(value="prtotype")
@Entity
@Table(name="`order`")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;
    
    //private List<Pizza> pizzas;
	@ElementCollection
	@JoinTable(name="pizzas_in_order", joinColumns=@JoinColumn(name="order_id"))
	@MapKeyColumn(name="pizza_id")
	@Column(name="qnt")
    private Map<Pizza, Integer> pizzas;
	
	@ManyToOne//(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
    private String name;
    
    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

    private Double totalCost;

    @Transient
    @Autowired
    private TotalOrderCostCalculator totalOrderCostCalculator;

    static int count;
    
    
    public Order() {
        name = Integer.toString(count++);
    }   

    public Order(Customer customer, Map<Pizza, Integer> pizzas) {
        this.pizzas = pizzas;
        this.customer = customer;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Map<Pizza, Integer> pizzas) {
        this.pizzas = pizzas;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void destroy(){
        System.out.println("Distroy");
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void calcTotalCost() {
        totalCost = totalOrderCostCalculator.calculateTotalOrderPrice(pizzas, customer.getAccumulativeCard().getTotalSum());
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", pizzas=" + pizzas.toString() + ", customer=" + customer + '}';
    }
    
    
    
}
