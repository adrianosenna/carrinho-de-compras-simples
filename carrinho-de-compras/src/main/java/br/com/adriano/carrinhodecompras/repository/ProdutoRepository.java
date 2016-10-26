package br.com.adriano.carrinhodecompras.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.adriano.carrinhodecompras.entity.ProdutoEntity;

/**
 * Classe de transações com banco de dados.
 * @author Adriano
 *
 */
public class ProdutoRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
	
	public ProdutoRepository() {
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_carrinho_de_compras");
		 
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	/**
	 * Retorna objeto {@link List} do tipo {@link ProdutoEntity} correspondente aos produtos gravados na base de dados.
	 * @return Objeto {@link List} do tipo {@link ProdutoEntity}
	 * @throws Exception Lança excessão caso ocorra erro durante a execução.
	 */
	public List<ProdutoEntity> listarProdutos() throws Exception{
		
		try {
			
			TypedQuery<ProdutoEntity> query = this.entityManager.createQuery("SELECT p FROM ProdutoEntity p ORDER BY p.nome", ProdutoEntity.class);
			
			return query.getResultList();

		} catch (NoResultException e) {
			
			return null;
			
		} catch (Exception e) {
			
			//Fazer log do erro
			e.printStackTrace();
			//Gerar mensagem de erro
			throw new Exception("Ocorreu um erro ao tentar obter os produtos");
			
			
		} finally {
			
			this.entityManager.clear();
			
		}
		
	}
	
}
