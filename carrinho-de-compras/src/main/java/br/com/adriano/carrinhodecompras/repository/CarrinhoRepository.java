package br.com.adriano.carrinhodecompras.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.adriano.carrinhodecompras.entity.CarrinhoEntity;
import br.com.adriano.carrinhodecompras.fixo.StatusCarrinho;

/**
 * Classe de transações com banco de dados.
 * @author Adriano
 *
 */
public class CarrinhoRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
	
	public CarrinhoRepository(){
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_carrinho_de_compras");
		 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	/**
	 * Obtem todos so resultados de produtos no carrinho em aberto.
	 * @return Objeto {@link List} do tipo {@link CarrinhoEntity}
	 * @throws Exception Lança excessão caso ocorra erro durante a execução.
	 */
	public List<CarrinhoEntity> obterCarrinho() throws Exception{
		
		try {
			
			TypedQuery<CarrinhoEntity> query = this.entityManager.createQuery("SELECT c FROM CarrinhoEntity c where c.status = :status ORDER BY c.id", CarrinhoEntity.class);
			
			query.setParameter("status", StatusCarrinho.ABERTO);
			
			return query.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		} catch (Exception e) {
			
			//Fazer log do erro
			e.printStackTrace();
			//Gerar uma mensagem de erro
			throw new Exception("Ocorreu um erro ao tentar obter o carrinho.");
			
		} 
		
	}
	
	/**
	 * Salva/atualiza o produto adicionado ao carrinho.
	 * @param entity - Entidade tipo {@link CarrinhoEntity} a ser mantida.
	 * @throws Exception Lança excessão caso ocorra erro durante a execução. Realiza rollback da transação.
	 */
	public void manterProduto(CarrinhoEntity entity) throws Exception {
		
		try {
			
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(entity);
			this.entityManager.getTransaction().commit();

		} catch (Exception e) {
		
			this.entityManager.getTransaction().rollback();
			//Fazer log do erro
			e.printStackTrace();
			
			//Gerar uma mensagem de erro
			throw new Exception("Ocorreu um erro ao tentar atualizar o carrinho.");
			
		} 
		
	}
	
	/**
	 * Remove o produto do carrinho de compras.
	 * @param idProduto - Identificador do produto. Objeto tipo {@link Integer}.
	 * @throws Exception Lança excessão caso ocorra erro durante a execução. Realiza rollback da transação.
	 */
	public void removerProduto(Integer idProduto) throws Exception {
		
		try {
			
			this.entityManager.getTransaction().begin();
			this.entityManager.createQuery("delete from CarrinhoEntity c where c.produtoEntity.id=:idProduto")
			.setParameter("idProduto", idProduto)
			.executeUpdate();
			this.entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			
			this.entityManager.getTransaction().rollback();
			//Fazer o log do erro
			e.printStackTrace();
			
			//Gerar uma mensagem do erro.
			throw new Exception("Ocorreu um erro ao tentar remover o produto do carrinho.");
			
		} 
		
		
	}
	
	/**
	 * Obtem o item do carrinho pelo identificador.
	 * @param idProduto - Identificador do produto. Objeto tipo {@link Integer}.
	 * @return Objeto entidade encontrada do tipo {@link CarrinhoEntity}.
	 * @throws Exception Lança excessão caso ocorra erro durante a execução.
	 */
	public CarrinhoEntity obterPorIdProduto(Integer idProduto) throws Exception{
		
		try {
			
			TypedQuery<CarrinhoEntity> query = this.entityManager.createQuery("SELECT c FROM CarrinhoEntity c where c.produtoEntity.id = :idProduto and c.status = :status", CarrinhoEntity.class)
					.setParameter("idProduto", idProduto)
					.setParameter("status", StatusCarrinho.ABERTO);
			
			return query.getSingleResult();
		
		} catch (NoResultException e){
				
			//Fazer o log do erro.
			e.printStackTrace();

			return null;
			
		} catch (Exception e) {
			
			//Fazer log do erro.
			e.printStackTrace();
			
			throw new Exception("Ocorreu um erro ao tentar obter o produto do carrinho.");
			
		} 
		
	}
	
	/**
	 * Fecha o carrinho de compras em aberto. Muda o status para FECHADO (ver em {@link StatusCarrinho} )
	 * @throws Exception Lança excessão caso ocorra erro durante a execução. Realiza rollback da transação.
	 */
	public void fecharCarrinho() throws Exception {
		
		try {
			
			this.entityManager.getTransaction().begin();
			this.entityManager.createQuery("update CarrinhoEntity c set c.status = :statusNew where c.status = :statusOld")
			.setParameter("statusNew", StatusCarrinho.FECHADO)
			.setParameter("statusOld", StatusCarrinho.ABERTO)
			.executeUpdate();
			this.entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			
			this.entityManager.getTransaction().rollback();
			//Fazer o log do erro
			e.printStackTrace();
			
			//Gerar uma mensagem do erro.
			throw new Exception("Ocorreu um erro ao tentar atualizar o status do carrinho.");
			
		} 
		
	}

}
