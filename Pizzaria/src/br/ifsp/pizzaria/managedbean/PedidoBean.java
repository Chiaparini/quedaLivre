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
import br.ifsp.pizzaria.repository.PedidoRepository;
import br.ifsp.pizzaria.repository.PizzaRepository;

@ManagedBean
public class PedidoBean implements Serializable {
	
	private int usuarioId;
	private List<Pizza> pizzas;
	private String status;
	private Usuario usuario;
	private double total;
	private Date data;
	private Pizza pizza;
	private int quantidade;
	private int idPizza;

	public String novoPedido() {
		
		
		try{
		
			for(Pizza p : this.pizzas) {
				if(p.getId() == this.idPizza) {
					this.pizza = p;
				}
			}
			System.out.println("AEAE " + this.pizza.getSabor());
			
			this.total = this.pizza.getPreco() * this.quantidade;
			
			this.pizzas.clear();
			
			for(int i = 1; i < this.quantidade; i++) {
				this.pizzas.add(this.pizza);
			}
			
			this.pizzas.add(this.pizza);
			Date d = new Date();
			this.data = d;
			this.status = "Aberto";
			
			Pedido pedido = new Pedido(this.pizzas, this.usuario, this.data, this.total, this.status);

			pedido.setPizzas(pizzas);
			pedido.setStatus(this.status);
			pedido.setTotal(this.total);
			
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
			EntityManager manager = factory.createEntityManager();
			PedidoRepository repo = new PedidoRepository(manager);
			manager.getTransaction().begin();
		
			repo.adiciona(pedido);
		
			manager.getTransaction().commit();
		
			manager.close();
		
			factory.close();
		
		return "SucessoPedido";	
		}catch(Exception e) {
			return "Ops";
		}	
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
}
