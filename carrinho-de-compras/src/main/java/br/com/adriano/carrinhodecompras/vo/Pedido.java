package br.com.adriano.carrinhodecompras.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pedido {

	private Long id;
	private String status;
	private Carrinho resumo;
	
	public Pedido(){}
	
	public Pedido(Long id, String status, Carrinho resumo){
		
		this.id = id;
		this.status = status;
		this.resumo = resumo;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Carrinho getResumo() {
		return resumo;
	}

	public void setResumo(Carrinho resumo) {
		this.resumo = resumo;
	}
	
}
