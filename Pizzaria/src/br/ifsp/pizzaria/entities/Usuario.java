package br.ifsp.pizzaria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="Usuario")
public class Usuario {
	@Id
	@GeneratedValue
	int id;
	
	@Column (name="nome", nullable=false, length=40)
	String nome;
	
	@Column (name="tipoUsuario", nullable=false, length=15)
	String tipoUsuario;
	
	@Column (name="telefone", nullable=false, length=11)
	String telefone;
	
	@Column (name="login", nullable=false, length=20, unique=true)
	String login;
	
	@Column (name="senha", nullable=false, length=255)
	String senha;
	
	@Transient
	String confirmaSenha;
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	@Column (name="rua", nullable=true, length=40)
	String rua;
	
	@Column (name="numero", nullable=true)
	int numero;
	
	@Column (name="bairro", nullable=true, length=20)
	String bairro;
	
	@Column (name="cep", nullable=true, length=9)
	String cep;
	
	@Column (name="cidade", nullable=true, length=20)
	String cidade;
	
	@Column (name="complemento", nullable=true, length=20)
	String complemento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
