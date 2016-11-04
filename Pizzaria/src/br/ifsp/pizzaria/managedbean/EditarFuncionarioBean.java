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
public class EditarFuncionarioBean {
	public EditarFuncionarioBean(){
		usu = SessionUtils.getUser();
		id = usu.getId();
		nome = usu.getNome();
		login = usu.getLogin();
		tipoUsuario = usu.getTipoUsuario();
		telefone = usu.getTelefone();
		login = usu.getLogin();
		
		
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

	private String nome;
	private String tipoUsuario;
	private String telefone;
	private String login;
	private String senha;
	private String confirmaSenha;
	
	public String getNome() {
		
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
