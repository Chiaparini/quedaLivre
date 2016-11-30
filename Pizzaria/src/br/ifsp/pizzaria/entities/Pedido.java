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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table (name="Pedido")
public class Pedido {
	
	public Pedido(){
		
	}
	
	public Pedido(List<Pizza> pizzas, Usuario usuario, double total,Date data, String status){
		this.pizzas = pizzas;
		this.usuario = usuario;
		this.total = total;
		this.data = data;
		this.status = status;
	}
	
	
	@Id
	@GeneratedValue
	int id;
	
	@Column (name="status", nullable=false, length=40)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@Column (name="data", nullable=false)		
	private Date data;
	
	@Column (name="total", nullable=false)
	private double total;
	
	//@ManyToMany
	//@JoinTable(name="pedido_has_pizza", joinColumns = {@JoinColumn(name = "id_pedido")}, inverseJoinColumns = {@JoinColumn(name = "id_pizza")})
    @ManyToMany(cascade = {CascadeType.MERGE})
	private List<Pizza> pizzas  = new ArrayList<>();

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


	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
