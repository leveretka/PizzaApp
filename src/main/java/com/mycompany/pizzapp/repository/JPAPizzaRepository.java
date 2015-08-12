package com.mycompany.pizzapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.domain.PizzaType;

@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

	@PersistenceContext(name="HibernatePostgreSql")
	private EntityManager em;
	
	public List<Pizza> getAllPizzas() {
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
		List<Pizza> pizzas = query.getResultList();
		return pizzas;
	}
	
	@Override
	public Pizza getPizzaByID(Integer id) {
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where p.id = :id", Pizza.class);
		query.setParameter("id", id);
		Pizza pizza = query.getSingleResult();
		return pizza;
	}

	@Transactional
	@Override
	public Integer save(Pizza p) {
		if (p.getId() != null) {
			if (getPizzaByID(p.getId()) == null) {
				throw new IllegalArgumentException("Wrong id: " + p.getId() + "for pizza!");
			}
			Pizza pizza = getPizzaByID(p.getId());
			
			em.merge(pizza);
			pizza.setName(p.getName());
			pizza.setPrice(p.getPrice());
			pizza.setType(p.getType());
		} else {
			em.persist(p);
		}
		return p.getId();
	}

	@Override
	public List<Pizza> getAllPizzasWithType(PizzaType type) {
		return em.createQuery("select p from Pizza p where p.type = :t", Pizza.class)
				.setParameter("t", type)
				.getResultList();
	}

}
