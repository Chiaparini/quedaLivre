package br.ifsp.pizzaria.entities;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
<<<<<<< HEAD
import javax.persistence.ManyToMany;
=======
>>>>>>> 7476ee0cd6a887da908f5647dd1202a961eb39b6
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="Pedido")
public class Pedido {
	
	public Pedido(List<Pizza> pizzas, Usuario usuario, Date data, double total, String status){
		this.pizzas = pizzas;
		this.usuario = usuario;
		this.data = data;
		this.total = total;
		this.status = status;
	}
	
	
	@Id
	@GeneratedValue
	int id;
	
	@Column (name="status", nullable=false, length=40)
	private String status;
	
<<<<<<< HEAD
=======
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="pedido_has_pizza",joinColumns={@JoinColumn(name="pedido_id", referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="pizza_id", referencedColumnName="id")})
	private List<Pizza> pizzas;
	
>>>>>>> 7476ee0cd6a887da908f5647dd1202a961eb39b6
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@Column (name="data", nullable=false)
	private Date data;
	
	@Column (name="total", nullable=false)
	private double total;
	
	@ManyToMany
	@JoinTable(name="pedido_has_pizza", joinColumns = {@JoinColumn(name = "id_pedido")}, inverseJoinColumns = {@JoinColumn(name = "id_pizza")})
	public List<Pizza> pizzas = new ArrayList<Pizza>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
