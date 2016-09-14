
package br.ifsp.pizzaria.repository;

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
	public Usuario verificarLogin(Usuario usuario){
		try{
			Query query = this.manager.
						createQuery("SELECT u FROM Usuario u WHERE login = :login AND senha = :senha");
			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", usuario.getSenha());
			return (Usuario) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
		
		
	}
	
}
