package br.ifsp.pizzaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Pizza")
public class Pizza {
	
	public Pizza(String sabor, double preco, String descricao) {
		this.sabor = sabor;
		this.preco = preco;
		this.descricao = descricao;
	}

	@Id
	@GeneratedValue
	int id;
	
	@Column (name="sabor", nullable=false, length=20)
	private String sabor;
	
	@Column (name="preco", nullable=false)
	private double preco;
	
	@Column (name="descricao", nullable=false, length=100)
	private String descricao;

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

}
