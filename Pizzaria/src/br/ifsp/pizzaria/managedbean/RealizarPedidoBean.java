package br.ifsp.pizzaria.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.pizzaria.entities.Pedido;
import br.ifsp.pizzaria.entities.Pizza;
import br.ifsp.pizzaria.entities.Usuario;
import br.ifsp.pizzaria.filtros.SessionUtils;
import br.ifsp.pizzaria.repository.PedidoRepository;
import br.ifsp.pizzaria.repository.PizzaRepository;
import br.ifsp.pizzaria.repository.UsuarioRepository;

@ManagedBean
@ViewScoped
public class RealizarPedidoBean implements Serializable {
	
	public RealizarPedidoBean(){
		
	}
	
	private int usuarioId;
	private List<Pizza> pizzas = new ArrayList<>();
	private String status;
	private Usuario usuario;
	private double total;
	private Date data;
	private Pizza pizza;
	private int quantidade;
	private int idPizza;
	
	public boolean isReadonly() { 
	    return FacesContext.getCurrentInstance().getRenderResponse();
	}
	
	public void adicionaPizza(){
		try{
			EntityManagerFactory factory =
					Persistence.createEntityManagerFactory("pizzaria");
					
			EntityManager manager = factory.createEntityManager();
			
			PizzaRepository pizzaRepository = new PizzaRepository(manager);
			System.out.println("Test");
			System.out.println("Pizza id" + idPizza);
			pizza = pizzaRepository.busca(idPizza);
			System.out.println("Test2");
			pizzas.add(pizza);
			System.out.println("Test3");
			total += pizza.getPreco() * quantidade;
			System.out.println("Test4");
			System.out.println("Total = " + total);
			System.out.println("Pizzas "+ pizzas.get(0).getDescricao());
			//return "";
		}catch(Exception e){
			//return "fasgasgas";
		}
	}
	
	public String realizarPedido(){
		try{
			usuario = SessionUtils.getUser();
			usuarioId = usuario.getId();
		
			EntityManagerFactory factory = 
					Persistence.createEntityManagerFactory("pizzaria");
			
			EntityManager manager = factory.createEntityManager();

			PedidoRepository pedidoRepository = new PedidoRepository(manager);
			
			UsuarioRepository usuRepo = new UsuarioRepository(manager);
			usuario =  usuRepo.busca(usuarioId);
			status= "aberto";
			System.out.println("Pizza para passar ao objeto: " + pizzas.get(0).getSabor());
			data = new Date();
			Pedido pedido = new Pedido(pizzas, usuario, total, data, status);

			System.out.println("Pizza para passar ao persistir: " + pedido.getPizzas().get(0).getSabor());

			manager.getTransaction().begin();
			
			pedidoRepository.adiciona(pedido);
			
			manager.getTransaction().commit();
			
			manager.close();
			
			factory.close();
			
			return "SucessoPedido";	
		}catch(Exception e) {
            e.printStackTrace();
			return "Ops";
			
		}	
		
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

	private Date getData() {
		return data;
	}

	private void setData(Date data) {
		this.data = data;
	}
}
