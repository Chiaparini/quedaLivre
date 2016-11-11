
package br.ifsp.pizzaria.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ifsp.pizzaria.entities.Usuario;

public class UsuarioRepository {
	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager){
		this.manager = manager;
	}
	
	public void adiciona(Usuario usuario){
		this.manager.persist(usuario);
	}
	
	public Usuario busca(int id){
		return this.manager.find(Usuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Usuario verificarLogin(String login, String senha){
		try{
			Query query = this.manager.
						createQuery("SELECT u FROM Usuario u WHERE login = :login AND senha = :senha");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			return (Usuario) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> verificarValorLogin(String login){
		try{
			Query query = this.manager.
						createQuery("SELECT u FROM Usuario u WHERE login = :login");
			query.setParameter("login", login);
			return query.getResultList();
		}
		catch(NoResultException e){
			return null;
		}
		
		
	}
	
}
