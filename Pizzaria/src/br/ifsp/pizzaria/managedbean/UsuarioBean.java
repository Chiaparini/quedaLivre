package br.ifsp.pizzaria.managedbean;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.pizzaria.entities.Usuario;
import br.ifsp.pizzaria.repository.UsuarioRepository;

@ManagedBean
public class UsuarioBean {

	private int id;
	private String nome;
	private String tipoUsuario;
	private String telefone;
	private String login;
	private String senha;
	private String confirmaSenha;
	private String rua;
	private int numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String complemento;
	
	public String cadastrarUsuario(String tipo){
		
		
		Usuario usu = new Usuario();
		
		usu.setNome(nome);
		usu.setTipoUsuario(tipo);
		usu.setTelefone(telefone);
		usu.setLogin(login);
		usu.setSenha(senha);
		usu.setConfirmaSenha(confirmaSenha);
		usu.setRua(rua);
		usu.setNumero(numero);
		usu.setBairro(bairro);
		usu.setCep(cep);
		usu.setCidade(cidade);
		usu.setComplemento(complemento);
		
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
		
		if(senha.equals(confirmaSenha) || senha.equals("")){
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
			
			EntityManager em = factory.createEntityManager();
			
			UsuarioRepository repo = new UsuarioRepository(em);
			
			em.getTransaction().begin();
			
			//Usuario usu = repo.busca(id);
			
			Usuario usu = (Usuario) em.find(Usuario.class, id);
			
			usu.setNome(nome);
			usu.setTelefone(telefone);
			usu.setLogin(login);
			if(!senha.equals("")){
				usu.setSenha(senha);
				usu.setConfirmaSenha(confirmaSenha);
			}
			usu.setRua(rua);
			usu.setNumero(numero);
			usu.setBairro(bairro);
			usu.setCep(cep);
			usu.setCidade(cidade);
			usu.setComplemento(complemento);

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
	
	public String verLogin(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pizzaria");
		EntityManager em = factory.createEntityManager();
		
		UsuarioRepository repo = new UsuarioRepository(em);
		Usuario usuario = new Usuario();
		Usuario result = new Usuario();
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		
		em.getTransaction().begin();
		
		usuario = repo.verificarLogin(usuario);
		
		em.getTransaction().commit();
		
		em.close();
		factory.close();
		
		if(usuario == null){
			return "Senha Inv�lida";
		}
		else{
			
			id = usuario.getId();
			nome = usuario.getNome();
			telefone = usuario.getTelefone();
			login = usuario.getLogin();
			senha = "";
			rua = usuario.getRua();
			numero = usuario.getNumero();
			bairro = usuario.getBairro();
			cep = usuario.getCep();
			cidade = usuario.getCidade();
			complemento = usuario.getComplemento();
			
			return "EditarFuncionario";
		}
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
