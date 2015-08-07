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
		return query.getResultList();
	}
	
	@Override
	public Pizza getPizzaByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Integer save(Pizza p) {
		em.persist(p);
		return p.getId();
	}

	@Override
	public List<Pizza> getAllPizzasWithType(PizzaType type) {
		return em.createQuery("select p from Pizza p where p.type = :t", Pizza.class)
				.setParameter("t", type)
				.getResultList();
	}

}
