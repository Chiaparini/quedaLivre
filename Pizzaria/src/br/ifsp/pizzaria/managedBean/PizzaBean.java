package br.ifsp.pizzaria.managedBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.pizzaria.entities.Pizza;
import br.ifsp.pizzaria.repository.PizzaRepository;

@ManagedBean
public class PizzaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String sabor;
	private double preco;
	private String descricao;
	
	public void cadastrarPizza(){
		
		Pizza pizza = new Pizza(sabor,preco,descricao);
		
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("pizza");
		
		EntityManager manager = factory.createEntityManager();

		PizzaRepository pizzaRepository = new PizzaRepository(manager);
		
		manager.getTransaction().begin();
		
		pizzaRepository.adiciona(pizza);
		
		manager.getTransaction().commit();
		
		factory.close();
		
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
