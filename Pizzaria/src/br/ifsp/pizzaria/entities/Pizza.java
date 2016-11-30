package br.ifsp.pizzaria.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name="Pizza")
public class Pizza {
	
	public Pizza(){
		
	}
	
	public Pizza(String sabor, double preco, String descricao) {
		this.sabor = sabor;
		this.preco = preco;
		this.descricao = descricao;
	}

	@Id
	@GeneratedValue
	int id;
	
	public void setId(int id) {
		this.id = id;
	}

	@Column (name="sabor", nullable=false, length=20)
	private String sabor;
	
	@Column (name="preco", nullable=false)
	private double preco;
	
	@Column (name="descricao", nullable=false, length=100)
	private String descricao;
	
    @ManyToMany(mappedBy = "pizzas")
	private Set<Pedido> pedido = new HashSet<>();
	
	 
    
	public int getId(){
		return id;
	}
	
	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(Set<Pedido> pedido) {
		this.pedido = pedido;
	}

}
