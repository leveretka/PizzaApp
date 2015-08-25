/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.domain;

import javax.persistence.*;

/**
 *
 * @author margarita
 */

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;
	
    private String name;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="accumulativeCard_id")
    private AccumulativeCard accumulativeCard;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer() {
	}

	public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + '}';
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

	public AccumulativeCard getAccumulativeCard() {
		return accumulativeCard;
	}

	public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
		this.accumulativeCard = accumulativeCard;
	}
    
    
}
