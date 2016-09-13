package br.ifsp.pizzaria.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.pizzaria.entities.Pizza;
import br.ifsp.pizzaria.repository.PizzaRepository;

@ManagedBean
public class PizzaBean implements Serializable{

	Pizza pizza = new Pizza();
	
	public PizzaBean(){
	}
	private static final long serialVersionUID = 1L;
	private int id;
	private String sabor;
	private double preco;
	private String descricao;
	
	public String cadastrarPizza(){
		try{
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
		}catch(Exception e){
			return "Ops";
		}
		
	}
	
	public String carregarEdicao(){
		buscar();
		System.out.println("id :" + this.id);
		return "EditarPizza";
	}
	
	public String editarPizza(){
		
		try{
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("pizzaria");
				
		EntityManager manager = factory.createEntityManager();
				
		PizzaRepository pizzaRepository = new PizzaRepository(manager);

		System.out.println("Id do objeto : " + pizza.getId() );

		Pizza pizza = pizzaRepository.busca(id);
		
		System.out.println("Pizza atual" + pizza.getDescricao() + " sabor: "+ pizza.getSabor()+ " preco:" + pizza.getPreco());

		System.out.println("Alterando para" + descricao + " sabor: "+ sabor+ " preco:" + preco);
		
		pizza.setDescricao(this.descricao);
		pizza.setPreco(this.preco);
		pizza.setSabor(this.sabor);
		
		manager.getTransaction().begin();
		
		manager.merge(pizza);
		
		manager.getTransaction().commit();
		
		manager.close();
		
		factory.close();
		
		return "SucessoEdicaoPizza";
		
		}catch(Exception e){
			return "Ops";
		}
		
	}
	
	public List<SelectItem> selecaoDePizzas(){

		   List<SelectItem> items = new ArrayList<SelectItem>();
		   List<Pizza> pizzaList = retornaTodos();
		    for(Pizza pizza: pizzaList){
		       items.add(new SelectItem(pizza.getId(), pizza.getSabor()));
		   }

		   return items;
	}	
	
	

	private void buscar(){
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("pizzaria");
				System.out.println("Procurando por pizza de id :" + id);
				
				EntityManager manager = factory.createEntityManager();
				
				PizzaRepository pizzaRepository = new PizzaRepository(manager);
				
				Pizza pizza = pizzaRepository.busca(id);
				
				System.out.println("Pizza: " + pizza.getSabor() + " - " + pizza.getId());
				
				manager.close();
				
				factory.close();
				
				this.descricao = pizza.getDescricao();
				this.sabor = pizza.getSabor();
				this.id = pizza.getId();
				this.preco = pizza.getPreco();
				this.pizza = pizza;
	}
	
	public List<Pizza> retornaTodos(){
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("pizzaria");
		
		EntityManager manager = factory.createEntityManager();
		
		PizzaRepository pizzaRepository = new PizzaRepository(manager);

		   List<Pizza> pizzaList = pizzaRepository.buscaTodas();
		   
		   manager.close();
		     
		   factory.close();
		   
		   return pizzaList;
	}
	
	public String deletar(){
		try{
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("pizzaria");
		
		EntityManager manager = factory.createEntityManager();
		
		PizzaRepository pizzaRepository = new PizzaRepository(manager);
		
		manager.getTransaction().begin();

		pizzaRepository.deletar(id);
			
		manager.getTransaction().commit();
		
		manager.close();
		     
	    factory.close();
		   
		return "SucessoDelecaoPizza";
		}catch(Exception e){
			return "Ops";
		}
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
		System.out.println("setting id : "+ id);
	}
	
	public String goToIndex(){
		return "index";
	}
	
	public boolean isReadonly() { 
	    return FacesContext.getCurrentInstance().getRenderResponse();
	}
}
