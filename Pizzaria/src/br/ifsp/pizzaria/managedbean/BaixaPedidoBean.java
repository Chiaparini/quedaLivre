package br.ifsp.pizzaria.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
public class BaixaPedidoBean implements Serializable {
	
	public BaixaPedidoBean(){
		
	}
	
	private int id;
	private int usuarioId;
	private List<Pizza> pizzas = new ArrayList<>();
	private String status;
	private Usuario usuario;
	private double total;
	private Pizza pizza;
	private int quantidade;
	private int idPizza;
	private Date data;
	
	public boolean isReadonly() { 
	    return FacesContext.getCurrentInstance().getRenderResponse();
	}
	
	public List<SelectItem> selecionarPedidos(){

		   List<SelectItem> items = new ArrayList<SelectItem>();
		   List<Pedido> pedidoList = buscaTodos();
		    for(Pedido pedido: pedidoList){
		       items.add(new SelectItem(pedido.getId(),"Total:" + pedido.getTotal()));
		   }

		   return items;
	}
	
	public List<Pedido> buscaTodos() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
		EntityManager manager = factory.createEntityManager();
		PedidoRepository repoPedido = new PedidoRepository(manager);
		
		manager.getTransaction().begin();
		
		List<Pedido> pedidos = repoPedido.buscaTodosAberto();
		
		manager.close();
		
		factory.close();
		
		return pedidos;
	}
	
	public String fechaPedido(){
		
		try{
			EntityManagerFactory factory =
					Persistence.createEntityManagerFactory("pizzaria");
					
			EntityManager manager = factory.createEntityManager();
					
			PedidoRepository pedidoRepository = new PedidoRepository(manager);

			System.out.println("Id do objeto pedio : " + id);

			Pedido pedido = pedidoRepository.busca(id);
			
			data = new Date();
			
			pedido.setData(data);
			pedido.setStatus("fechado");
			
			manager.getTransaction().begin();
			
			manager.merge(pedido);
			
			manager.getTransaction().commit();
			
			manager.close();
			
			factory.close();
			
			return "";
		}catch(Exception e){
			
			return "Ops";
		}
				
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
