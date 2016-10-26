package br.com.adriano.carrinhodecompras.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.adriano.carrinhodecompras.entity.PedidoEntity;

public class PedidoRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
	
	public PedidoRepository() {
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_carrinho_de_compras");
		 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	public PedidoEntity salvarPedido(PedidoEntity entity) throws Exception{
		
		try {
			
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(entity);
			this.entityManager.getTransaction().commit();
			
			return entity;
			
		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();
			//Fazer o log do erro
			e.printStackTrace();
			//Gerar mensagem de erro
			throw new Exception("Ocorreu um erro ao tentar salvar o pedido.");
			
		} 
		
	}

}
