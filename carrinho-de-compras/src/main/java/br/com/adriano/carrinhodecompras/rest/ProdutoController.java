package br.com.adriano.carrinhodecompras.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import br.com.adriano.carrinhodecompras.entity.ProdutoEntity;
//import br.com.adriano.carrinhodecompras.mock.ProdutoMock;
import br.com.adriano.carrinhodecompras.repository.ProdutoRepository;

/**
 * Classe de serviço de produtos cadastrados para venda.
 * @author Adriano
 *
 */
@Path("produtos")
public class ProdutoController {
	
	private ProdutoRepository repository = new ProdutoRepository();
	
	/**
	 * Retorna todos os produtos disponíveis para venda.
	 * @return Lista de produtos.
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProdutoEntity> getProdutos() {
		
		try {
			
			return repository.listarProdutos();
			
		} catch (Exception e) {
			
			//Lançar exceção
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
			
		}
//		        return ProdutoMock.getProdutos(); // apenas mock
    }

}
