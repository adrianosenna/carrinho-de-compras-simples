package br.com.adriano.carrinhodecompras.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.adriano.carrinhodecompras.entity.CarrinhoEntity;
import br.com.adriano.carrinhodecompras.entity.ProdutoEntity;
import br.com.adriano.carrinhodecompras.fixo.StatusCarrinho;
import br.com.adriano.carrinhodecompras.repository.CarrinhoRepository;
import br.com.adriano.carrinhodecompras.vo.Carrinho;
import br.com.adriano.carrinhodecompras.vo.Produto;

/**
 * Classe de fachada para carrinho de compras.
 * @author Adriano
 *
 */
public class CarrinhoServiceFacade {
	
	private CarrinhoRepository carrinhoRepository = new CarrinhoRepository();

	/**
	 * Obtem lista de produtos no carrinho de compras.
	 * @return Objeto {@link Carrinho} com lista de produtos e outras informações.
	 * @throws Exception Lança excessão caso ocorra erro durante a execução.
	 */
	public Carrinho obterCarrinho() throws Exception{
		
		List<CarrinhoEntity> result = carrinhoRepository.obterCarrinho();
		
		Carrinho carrinho = new Carrinho();
		
		carrinho.setProdutos(new ArrayList<Produto>());
		
		Double valorTotal = 0.0;
		
		for (CarrinhoEntity rs : result) {
			
			Produto produto = new Produto(rs.getProdutoEntity().getId(), rs.getProdutoEntity().getNome(), rs.getProdutoEntity().getValorUnitario(), rs.getQuantidade());
			
			carrinho.getProdutos().add(produto);
			
			// Calcula o valor total do carrinho
			valorTotal += rs.getQuantidade() * rs.getProdutoEntity().getValorUnitario();
		}
		
		carrinho.setValorTotal(valorTotal);
		
		return carrinho;
	}
	
	/**
	 * Adiciona o produto ao carrinho de compras.
	 * @param idProduto Identificador do produto a ser adicionado. Objeto tipo {@link Integer}
	 * @param quantidade Quantidade do produto a ser adicionado. Objeto tipo {@link Integer}
	 * @return Objeto do tipo {@link Carrinho} correspondente ao carrinho de compras atualizado.
	 * @throws Exception Lança excessão caso ocorra erro durante a execução.
	 */
	public Carrinho adicionarProduto(Integer idProduto, Integer quantidade) throws Exception{
		
		
		List<CarrinhoEntity> lisatCarrinhoEntity = carrinhoRepository.obterCarrinho();
		
		CarrinhoEntity entity = null;
		
		for (CarrinhoEntity carrinhoEntity : lisatCarrinhoEntity) {
			
			if(carrinhoEntity.getProdutoEntity().getId().equals(idProduto)) {
				
				entity = carrinhoEntity;
				
				break;
				
			}
		}
		
		//Produto encontrado no carrinho
		if(entity != null){
			
			entity.setQuantidade(entity.getQuantidade() + quantidade);
			
		} else { //Produto novo no carrinho
			
			ProdutoEntity produtoEntity = new ProdutoEntity();
			
			produtoEntity.setId(idProduto);
			
			entity = new CarrinhoEntity();
			
			entity.setQuantidade(quantidade);
			
			entity.setProdutoEntity(produtoEntity);
			
			entity.setStatus(StatusCarrinho.ABERTO);
			
		}
		
		carrinhoRepository.manterProduto(entity);
		
		return obterCarrinho();
		
	}
	
	/**
	 * Remove o produto do carrinho de compras.
	 * @param idProduto Identificado do produto a ser removido. Objeto do tipo {@link Integer}
	 * @param quantidade Quantidade do produto a ser removido. Objeto do tipo {@link Integer}
	 * @return Objeto do tipo {@link Carrinho} correspondente ao carrinho de compras atualizado.
	 * @throws Exception Lança excessão caso ocorra erro durante a execução.
	 */
	public Carrinho removerProduto(Integer idProduto, Integer quantidade) throws Exception{
		
		
		CarrinhoEntity entity = carrinhoRepository.obterPorIdProduto(idProduto);
		
		//Se a quantidade de produto for igual, significa que todo o produto foi removido do carrinho
		if(entity.getQuantidade() == quantidade){
			
			carrinhoRepository.removerProduto(idProduto);
			
		} else {
			
			//Atualiza apenas a quantidade de extraída do carrinho
			entity.setQuantidade(entity.getQuantidade() - quantidade);
			
			carrinhoRepository.manterProduto(entity);
			
		}
		
		return obterCarrinho();
		
	}
	
}