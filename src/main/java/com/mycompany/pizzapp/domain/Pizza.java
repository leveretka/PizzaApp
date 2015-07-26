/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.domain;

/**
 *
 * @author margarita
 */
public class Pizza {
    private Integer id;
    private String name;
    private Double price;
    private PizzaType type; 
    
    public Pizza(Integer id, String name, Double price, PizzaType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + '}';
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
}
