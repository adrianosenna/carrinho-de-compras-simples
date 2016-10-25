package br.com.adriano.carrinhodecompras.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.adriano.carrinhodecompras.vo.Produto;

public class ProdutoMock {

	public static List<Produto> getProdutos(){
		
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(new Produto(1, "Computador", 1200.0, 1));
		produtos.add(new Produto(2, "Televisor", 900.0, 1));
		produtos.add(new Produto(3, "Fogão", 550.0, 1));
		
		return produtos;
	}
	
}
