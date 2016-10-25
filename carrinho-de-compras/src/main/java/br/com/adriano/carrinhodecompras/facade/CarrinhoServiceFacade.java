package br.com.adriano.carrinhodecompras.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.adriano.carrinhodecompras.entity.CarrinhoEntity;
import br.com.adriano.carrinhodecompras.entity.ProdutoEntity;
import br.com.adriano.carrinhodecompras.repository.CarrinhoRepository;
import br.com.adriano.carrinhodecompras.vo.Carrinho;
import br.com.adriano.carrinhodecompras.vo.Produto;

public class CarrinhoServiceFacade {
	
	private CarrinhoRepository carrinhoRepository = new CarrinhoRepository();

	public Carrinho obterCarrinho(){
		
		List<CarrinhoEntity> result = carrinhoRepository.obterCarrinho();
		
		Carrinho carrinho = new Carrinho();
		
		carrinho.setProdutos(new ArrayList<Produto>());
		
		Double valorTotal = 0.0;
		
		for (CarrinhoEntity rs : result) {
			
			Produto produto = new Produto(rs.getProdutoEntity().getId(), rs.getProdutoEntity().getNome(), rs.getProdutoEntity().getValorUnitario(), rs.getQuantidade());
			
			carrinho.getProdutos().add(produto);
			
			valorTotal += rs.getQuantidade() * rs.getProdutoEntity().getValorUnitario();
		}
		
		carrinho.setValorTotal(valorTotal);
		
		return carrinho;
	}
	
	public Carrinho adicionarProduto(Integer idProduto, Integer quantidade){
		
		
		List<CarrinhoEntity> lisatCarrinhoEntity = carrinhoRepository.obterCarrinho();
		
		CarrinhoEntity entity = null;
		
		for (CarrinhoEntity carrinhoEntity : lisatCarrinhoEntity) {
			
			if(carrinhoEntity.getProdutoEntity().getId().equals(idProduto)) {
				
				entity = carrinhoEntity;
				
				break;
				
			}
		}
		
		if(entity != null){
			
			entity.setQuantidade(entity.getQuantidade() + quantidade);
			
		} else {
			
			ProdutoEntity produtoEntity = new ProdutoEntity();
			
			produtoEntity.setId(idProduto);
			
			entity = new CarrinhoEntity();
			
			entity.setQuantidade(quantidade);
			
			entity.setProdutoEntity(produtoEntity);
			
		}
		
		carrinhoRepository.manterProduto(entity);
		
		return obterCarrinho();
		
	}
	
	public Carrinho removerProduto(Integer idProduto, Integer quantidade){
		
		
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
