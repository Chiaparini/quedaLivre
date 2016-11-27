package br.ifsp.pizzaria.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ifsp.pizzaria.entities.Pedido;
import br.ifsp.pizzaria.entities.Pizza;

public class PedidoRepository {

	public PedidoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	private EntityManager manager;
	
	public void adiciona(Pedido pedido){
		System.out.println("Pizza para persistir: " + pedido.getPizzas().get(0).getSabor());
		this.manager.persist(pedido);
	}
	
	public Pedido busca(int id) {
		return this.manager.find(Pedido.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> pedidosUsuario(int id) {
		Query query = manager.createQuery(" FROM Pedido WHERE usuario_id = :id");
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> buscaTodos() {
		Query query = this.manager.createQuery(" FROM Pedido ");
		return query.getResultList();
	}
	
	
	
}
