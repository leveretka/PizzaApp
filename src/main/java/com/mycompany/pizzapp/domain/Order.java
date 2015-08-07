/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
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
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
    private String name;
    
    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;
    
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

    
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", pizzas=" + pizzas.toString() + ", customer=" + customer + '}';
    }
    
    
    
}
