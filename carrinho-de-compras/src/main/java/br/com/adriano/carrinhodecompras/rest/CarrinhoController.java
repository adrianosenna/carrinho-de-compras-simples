package br.com.adriano.carrinhodecompras.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import br.com.adriano.carrinhodecompras.facade.CarrinhoServiceFacade;
import br.com.adriano.carrinhodecompras.vo.Carrinho;

/**
 * Classe de serviço Rest para carrinho de compras
 * @author Adriano
 *
 */
@Path("carrinho")
public class CarrinhoController {
	
	private CarrinhoServiceFacade facade = new CarrinhoServiceFacade();
	
	/**
	 * Envia informações do carrinho de compras com situação em aberto.
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho getCarrinho() {
		
		try {
			
			return facade.obterCarrinho();
			
		} catch (Exception e) {
			//Lançar exceção
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	/**
	 * Recebe produto adicionado e retorna o carrinho de compras atualizado.
	 * @param id Identificador do produto a ser adicionado.
	 * @param quantidade Quantidade do produto a ser adicionado.
	 * @return Carrinho de compras atualizado.
	 */
	@PUT
	@Path("/{id}/{qtd}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho putProduto(@PathParam("id") Integer id, @PathParam("qtd") Integer quantidade) {
		
		try {
			
			return facade.adicionarProduto(id, quantidade);
			
		} catch (Exception e) {
			//Lançar exceção
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	/**
	 * Recebe produto a ser removido e retorna o carrinho de comrpas atualizado.
	 * @param id Identidicador do produto a ser removido.
	 * @param quantidade Quantidade do produto a ser removido.
	 * @return Carrinho de compras atualizado.
	 */
	@DELETE
	@Path("/{id}/{qtd}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho deleteProduto(@PathParam("id") Integer id, @PathParam("qtd") Integer quantidade) {
		
		try {
			
			return facade.removerProduto(id, quantidade);
			
		} catch (Exception e) {

			//Lançar exceção
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
}
