package br.com.adriano.carrinhodecompras.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.adriano.carrinhodecompras.entity.ProdutoEntity;
import br.com.adriano.carrinhodecompras.repository.ProdutoRepository;

@Path("produtos")
public class ProdutoController {
	
	private ProdutoRepository repository = new ProdutoRepository();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProdutoEntity> getProdutos() {
		
		return repository.listarProdutos();
//        return ProdutoMock.getProdutos();
    }

}
