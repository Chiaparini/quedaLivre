package br.ifsp.pizzaria.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.pizzaria.entities.Pizza;
import br.ifsp.pizzaria.repository.PizzaRepository;

@ManagedBean
public class PizzaBean implements Serializable{

	public PizzaBean(){
	}
	private static final long serialVersionUID = 1L;
	private int id;
	private String sabor;
	private double preco;
	private String descricao;
	
	public String cadastrarPizza(){
		
		Pizza pizza = new Pizza(sabor,preco,descricao);
		
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("pizzaria");
		
		EntityManager manager = factory.createEntityManager();

		PizzaRepository pizzaRepository = new PizzaRepository(manager);
		
		manager.getTransaction().begin();
		
		pizzaRepository.adiciona(pizza);
		
		manager.getTransaction().commit();
		
		
		manager.close();
		
		factory.close();
		
		System.out.println("Pizza: " + sabor + " preco: " + preco);
		
		return "SucessoPizza";
		
	}
	
	
	public List<SelectItem> allPizzas(){
		
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("pizzaria");
		
		EntityManager manager = factory.createEntityManager();
		
		PizzaRepository pizzaRepository = new PizzaRepository(manager);

		   List<SelectItem> items = new ArrayList<SelectItem>();
		   List<Pizza> pizzaList = pizzaRepository.buscaTodas();
		    for(Pizza pizza: pizzaList){
		       items.add(new SelectItem(pizza.getId(), pizza.getSabor()));
		   }
		    
		    manager.close();
		     
		    factory.close();
		     
		   return items;
	}	


	public Pizza buscar(int id){
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("pizzaria");
				System.out.println("Procurando por pizza de id :" + id);
				
				EntityManager manager = factory.createEntityManager();
				Pizza pizza = (Pizza) manager.find(Pizza.class, id);
				
				System.out.println("Pizza: " + pizza.getSabor() + " - " + pizza.getId());
				
				manager.close();
				
				factory.close();
				
				return pizza;
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
	public String getSabor() {
		return sabor;
	}

	public double getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
