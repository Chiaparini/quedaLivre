package br.ifsp.pizzaria.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import br.ifsp.pizzaria.entities.Usuario;
import br.ifsp.pizzaria.repository.UsuarioRepository;
import filtros.SessionUtils;

@ManagedBean
public class EditarClienteBean {
	
	
	public EditarClienteBean(){
		usu = SessionUtils.getUser();
		id = usu.getId();
		nome = usu.getNome();
		login = usu.getLogin();
		tipoUsuario = usu.getTipoUsuario();
		telefone = usu.getTelefone();
		login = usu.getLogin();
		rua = usu.getRua();
		numero = usu.getNumero();
		bairro = usu.getBairro();
		cep = usu.getCep();
		cidade = usu.getCidade();
		complemento = usu.getComplemento();
		
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
				
				
				System.out.println("Dados do usuário " + usu.getNome() + " alterados com sucesso");
				
				FacesContext fc = FacesContext.getCurrentInstance();
				
				ExternalContext ec = fc.getExternalContext();
				HttpSession session = (HttpSession)ec.getSession(false);
				session.setAttribute("usuario", usu);
				
				
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

	Usuario usu;
	
	private int id;
	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	
	
	public String getNome() {
		
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
