package br.ifsp.pizzaria.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.pizzaria.entities.Pedido;
import br.ifsp.pizzaria.entities.Pizza;
import br.ifsp.pizzaria.entities.Usuario;
import br.ifsp.pizzaria.filtros.SessionUtils;
import br.ifsp.pizzaria.repository.PedidoRepository;
import br.ifsp.pizzaria.repository.PizzaRepository;

@ManagedBean
public class PedidoBean implements Serializable {	
	
	private String nome;
	private int usuarioId;
	private List<Pizza> pizzas;
	private String status;
	private Usuario usuario;
	private double total;
	private Date data;
	private Pizza pizza;
	private int quantidade;
	private int idPizza;

	
	
	
	public List<Pedido> pedidos(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
		EntityManager manager = factory.createEntityManager();
		PedidoRepository repoPedido = new PedidoRepository(manager);
		
		manager.getTransaction().begin();
		List<Pedido> pedidos = repoPedido.buscaTodosDoUsuario(); 
		
		manager.close();
		
		factory.close();
		
		return pedidos;
	}
	
	public List<Pedido> historicoUsuario(int id){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
		EntityManager manager = factory.createEntityManager();
		PedidoRepository repoPedido = new PedidoRepository(manager);
		
		manager.getTransaction().begin();
		
		List<Pedido> pedidos = repoPedido.pedidosUsuario(id);
		
		manager.close();
		
		factory.close();
		
		return pedidos;
		
	}
	
	
	public List<Pedido> buscaTodos() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Pizzaria");
		EntityManager manager = factory.createEntityManager();
		PedidoRepository repoPedido = new PedidoRepository(manager);
		
		manager.getTransaction().begin();
		
		List<Pedido> pedidos = repoPedido.buscaTodos();
		
		manager.close();
		
		factory.close();
		
		return pedidos;
	}
	
	public List<Pizza> buscaTodasPizzas() {
		PizzaBean pb = new PizzaBean();
		this.pizzas = pb.retornaTodos();
		return pb.retornaTodos();
	}
	
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDate() {
		return data;
	}

	public void setDate(Date date) {
		this.data = date;
	}
	
	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
	public int getUsuarioId() {
		return usuarioId;
	}


	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public Pizza getPizza() {
		return pizza;
	}


	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getIdPizza() {
		return idPizza;
	}


	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}
	public String getNome() {
		return SessionUtils.getUser().getNome(); 

	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

}
