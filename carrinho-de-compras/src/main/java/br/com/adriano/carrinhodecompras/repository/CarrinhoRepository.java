package br.com.adriano.carrinhodecompras.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.adriano.carrinhodecompras.entity.CarrinhoEntity;

public class CarrinhoRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
	
	public CarrinhoRepository(){
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_compras");
		 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	public List<CarrinhoEntity> obterCarrinho(){
		
		TypedQuery<CarrinhoEntity> query = this.entityManager.createQuery("SELECT c FROM CarrinhoEntity c ORDER BY c.id", CarrinhoEntity.class);
		
		return query.getResultList();
		
	}
	
	public void manterProduto(CarrinhoEntity entity) {
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(entity);
		this.entityManager.getTransaction().commit();
		
	}
	
	public void removerProduto(Integer idProduto) {
		
		this.entityManager.getTransaction().begin();
		this.entityManager.createQuery("delete from CarrinhoEntity c where c.produtoEntity.id=:idProduto")
        .setParameter("idProduto", idProduto)
        .executeUpdate();
		
	}
	
	public CarrinhoEntity obterPorIdProduto(Integer idProduto){
		
		TypedQuery<CarrinhoEntity> query = this.entityManager.createQuery("SELECT c FROM CarrinhoEntity c where c.produtoEntity.id = :idProduto", CarrinhoEntity.class)
				.setParameter("idProduto", idProduto);
		
		return query.getSingleResult();
		
	}

}
