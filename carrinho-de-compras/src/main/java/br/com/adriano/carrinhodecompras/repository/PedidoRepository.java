package br.com.adriano.carrinhodecompras.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.adriano.carrinhodecompras.entity.PedidoEntity;

public class PedidoRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
	
	public PedidoRepository() {
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_compras");
		 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	public PedidoEntity salvarPedido(PedidoEntity entity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(entity);
		this.entityManager.flush();
		this.entityManager.getTransaction().commit();
		return entity;
	}

}
