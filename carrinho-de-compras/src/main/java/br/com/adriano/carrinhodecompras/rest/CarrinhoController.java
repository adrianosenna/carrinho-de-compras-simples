package br.com.adriano.carrinhodecompras.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.adriano.carrinhodecompras.facade.CarrinhoServiceFacade;
import br.com.adriano.carrinhodecompras.vo.Carrinho;

@Path("carrinho")
public class CarrinhoController {
	
	private CarrinhoServiceFacade facade = new CarrinhoServiceFacade();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho getCarrinho() {
		
		return facade.obterCarrinho();
//		return CarrinhoMock.getCarrinho();
	}
	
	@PUT
	@Path("/{id}/{qtd}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho putProduto(@PathParam("id") Integer id, @PathParam("qtd") Integer quantidade) {
		
		return facade.adicionarProduto(id, quantidade);
//        return CarrinhoMock.getCarrinho();
	}
	
	@DELETE
	@Path("/{id}/{qtd}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho deleteProduto(@PathParam("id") Integer id, @PathParam("qtd") Integer quantidade) {
		
		return facade.removerProduto(id, quantidade);
//		return CarrinhoMock.getCarrinho();
	}
	
}
