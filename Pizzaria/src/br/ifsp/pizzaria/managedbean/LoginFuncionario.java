package br.ifsp.pizzaria.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.pizzaria.entities.Pedido;
import br.ifsp.pizzaria.entities.Usuario;
import br.ifsp.pizzaria.repository.UsuarioRepository;

@ManagedBean
public class LoginFuncionario {
	private int id;
	private String nome;
	private String tipoUsuario = "funcionario";
	private String telefone;
	private String login;
	private String senha;
	private String confirmaSenha;
	private List<Pedido> pedidos;
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String cadastrarUsuario(String tipo){
		
		
		Usuario usu = new Usuario();
		
		usu.setNome(nome);
		usu.setTipoUsuario(tipo);
		usu.setTelefone(telefone);
		usu.setLogin(login);
		usu.setSenha(senha);
		usu.setConfirmaSenha(confirmaSenha);
		
		
		if(usu.getSenha().equals(usu.getConfirmaSenha())){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
		
		EntityManager em = factory.createEntityManager();
		
		UsuarioRepository repo = new UsuarioRepository(em);
		
		em.getTransaction().begin();
		
		repo.adiciona(usu);
		
		em.getTransaction().commit();
		
		em.close();
		factory.close();
		
		System.out.println("Usu�rio " + usu.getNome() + " cadastrado com sucesso");
		
		return "RespostaUsuario";
		
		}
		else{
			return "SenhaInvalida";
		}
	}
	
	public String alterarUsuario(){
		
		try{
			if(senha.equals(confirmaSenha) || senha.equals("")){
				
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
				
				EntityManager em = factory.createEntityManager();
				
				UsuarioRepository repo = new UsuarioRepository(em);
				
				em.getTransaction().begin();
				
				Usuario usu = repo.busca(id);
				
				//Usuario usu = (Usuario) em.find(Usuario.class, id);
				
				usu.setNome(nome);
				usu.setTelefone(telefone);
				usu.setLogin(login);
				if(!senha.equals("")){
					usu.setSenha(senha);
					usu.setConfirmaSenha(confirmaSenha);
				}
					
				em.merge(usu);
				
				em.getTransaction().commit();
				
				em.close();
				factory.close();
				
				
				System.out.println("Dados do usu�rio " + usu.getNome() + " alterados com sucesso");
				
				return "RespostaEditaUsuario";
			}
			else{
				return "SenhaInvalida";
			}
		}
		catch(NullPointerException e){
			return "Ops";
		}
	}
	
		
	public String verHistorico(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
		EntityManager em = factory.createEntityManager();
		
		UsuarioRepository repo = new UsuarioRepository(em);
		Usuario usuario = new Usuario();
		Usuario result = new Usuario();
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setTipoUsuario(tipoUsuario);
		
		
		em.getTransaction().begin();
		
		em.getTransaction().commit();
		
		em.close();
		factory.close();
		
		id = usuario.getId();
		nome = usuario.getNome();
		telefone = usuario.getTelefone();
		login = usuario.getLogin();
		senha = "";
					
		PedidoBean pedidob = new PedidoBean();
		this.pedidos = pedidob.historicoUsuario(id);
		return "HistoricoPedido";
	}
	
	
	
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

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
