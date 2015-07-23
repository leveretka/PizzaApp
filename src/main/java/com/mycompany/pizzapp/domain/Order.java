/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.domain;

import java.util.List;

/**
 *
 * @author margarita
 */
public class Order {
    
    private Integer id;
    private List<Pizza> pizzas;
    private Customer customer;

    public Order(Customer customer, List<Pizza> pizzas) {
        this.pizzas = pizzas;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", pizzas=" + pizzas.toString() + ", customer=" + customer + '}';
    }
    
    
    
}
