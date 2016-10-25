package br.com.adriano.carrinhodecompras.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.adriano.carrinhodecompras.entity.PedidoEntity;
import br.com.adriano.carrinhodecompras.fixo.StatusPedido;
import br.com.adriano.carrinhodecompras.repository.PedidoRepository;
import br.com.adriano.carrinhodecompras.vo.Carrinho;

@Path("pedido")
public class PedidoController {
	
	private PedidoRepository repository = new PedidoRepository();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoEntity postPedido(Carrinho carrinho) {
		
		PedidoEntity entity = new PedidoEntity();
		entity.setStatus(StatusPedido.AGUARDANDO_CONFIRMACAO_PGTO);
		
		return repository.salvarPedido(entity);
	}

}
