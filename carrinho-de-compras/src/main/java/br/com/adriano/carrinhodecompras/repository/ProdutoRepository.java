package br.com.adriano.carrinhodecompras.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.adriano.carrinhodecompras.entity.ProdutoEntity;

public class ProdutoRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
	
	public ProdutoRepository() {
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_compras");
		 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	public List<ProdutoEntity> listarProdutos(){
		
		TypedQuery<ProdutoEntity> query = this.entityManager.createQuery("SELECT p FROM ProdutoEntity p ORDER BY p.nome", ProdutoEntity.class);
		
		return query.getResultList();
		
	}
	
	public ProdutoEntity obterProduto(Integer id) {
		
		return this.entityManager.find(ProdutoEntity.class, id);
		
	}

}
