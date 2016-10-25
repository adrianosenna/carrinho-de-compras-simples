package br.com.adriano.carrinhodecompras.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.adriano.carrinhodecompras.entity.ProdutoEntity;
import br.com.adriano.carrinhodecompras.repository.ProdutoRepository;
import br.com.adriano.carrinhodecompras.vo.Produto;

public class ProdutoServiceFacade {

	private ProdutoRepository repository = new ProdutoRepository();
	
	public List<Produto> listarProdutos(){
		
		List<ProdutoEntity> result = repository.listarProdutos();
		
		List<Produto> listaProdutos = new ArrayList<>();
		
		for (ProdutoEntity rs : result) {
			listaProdutos.add(new Produto(rs.getId(), rs.getNome(), rs.getValorUnitario(), 0));
		}
		
		return listaProdutos;
	}
	
	public Produto obterProduto(Integer id){
		
		ProdutoEntity entity = repository.obterProduto(id);
		
		return new Produto(entity.getId(), entity.getNome(), entity.getValorUnitario(), 0);
		
	}

}
