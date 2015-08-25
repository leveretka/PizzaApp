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

	@OneToOne(mappedBy = "accumulativeCard")
	private Customer customer;
	
	@Column(name="total")
	private Double totalSum;

	public AccumulativeCard() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public void add(double sum) {
		this.totalSum += sum;
	}
	
	
}
