package br.ifsp.pizzaria.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ifsp.pizzaria.entities.Pizza;

public class PizzaRepository {
	
	public PizzaRepository(EntityManager manager) {
		this.manager = manager;
	}

	private EntityManager manager;
	
	public void adiciona(Pizza pizza){
		this.manager.persist(pizza);
	}
	
	public void deletar(int id){
		this.manager.remove(busca(id));
	}
	
	public Pizza busca(int id){
		return this.manager.find(Pizza.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pizza> buscaTodas() {
		Query query = this.manager.createQuery(" FROM Pizza ");
		return query.getResultList();
	}
	
	
}
