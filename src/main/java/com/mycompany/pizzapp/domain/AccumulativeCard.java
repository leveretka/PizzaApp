package com.mycompany.pizzapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccumulativeCard {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="addres_id")
	private Address address;
	
	@Column(name="total")
	private double totalSum;

	public AccumulativeCard() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}
	
	
}
