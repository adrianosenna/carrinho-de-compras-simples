package br.com.adriano.carrinhodecompras.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import br.com.adriano.carrinhodecompras.entity.PedidoEntity;
import br.com.adriano.carrinhodecompras.facade.PedidoServiceFacade;
import br.com.adriano.carrinhodecompras.vo.Carrinho;

/**
 * Classe de serviço Rest para finalização de compra.
 * @author Adriano
 *
 */
@Path("pedido")
public class PedidoController {
	
	private PedidoServiceFacade facade = new PedidoServiceFacade();
	
	/**
	 * Recebe carrinho de compras finalizado.
	 * @param carrinho Carrinho de compras.
	 * @return Dados do pedido, como número do protocólo e situação do pagamento.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoEntity postPedido(Carrinho carrinho) {
		
		try {
			
			return facade.salvarPedido();
			
		} catch (Exception e) {
			
			//Lançar exceção
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		
		}
	}

}
