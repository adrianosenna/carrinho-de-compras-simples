package br.com.adriano.carrinhodecompras.facade;

import br.com.adriano.carrinhodecompras.entity.PedidoEntity;
import br.com.adriano.carrinhodecompras.fixo.StatusPedido;
import br.com.adriano.carrinhodecompras.repository.CarrinhoRepository;
import br.com.adriano.carrinhodecompras.repository.PedidoRepository;

/**
 * Classe de fachada de serviço para finaliação de compra.
 * @author Adriano
 *
 */
public class PedidoServiceFacade {
	
	private PedidoRepository pedidoRepository = new PedidoRepository();
	
	private CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
	
	/**
	 * Registra a compra dos produtos adicionados ao carrinho.
	 * @return Objeto tipo {@link PedidoEnity} correspondente aos dados do pedido.
	 * @throws Exception Lança exceção caso ocorra erro durante a execução.
	 */
	public PedidoEntity salvarPedido() throws Exception{
		
		try {
			
			//Atualiza a situação do carrinho (ver classe StatusCarrinho)
			carrinhoRepository.fecharCarrinho();
			
		} catch (Exception e) {
			
			//Gerar mensagem de erro
			throw new Exception("Ocorreu um erro interno ao tentar finalizar a compra.");
			
		}
		
		PedidoEntity entity = new PedidoEntity();
		
		entity.setStatus(StatusPedido.AGUARDANDO_CONFIRMACAO_PGTO);
		
		try {
			
			//Registra a compra e gera o número de protocolo
			return pedidoRepository.salvarPedido(entity);
			
		} catch (Exception e) {
			
			//Gerar mensagem de erro.
			throw new Exception("Ocorreu um erro interno ao tentar finalizar a compra.");
		}
		
	}

}
