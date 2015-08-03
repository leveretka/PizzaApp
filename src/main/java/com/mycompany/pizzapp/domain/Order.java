/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.domain;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author margarita
 */

@Component(value="order")
@Scope(value="prtotype")
public class Order {
    
    private Integer id;
    private List<Pizza> pizzas;
    private Customer customer;
    private String name;
    
    static int count;
    
    
    public Order() {
        name = Integer.toString(count++);
    }   

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

    public void destroy(){
        System.out.println("Distroy");
    }

    
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", pizzas=" + pizzas.toString() + ", customer=" + customer + '}';
    }
    
    
    
}
