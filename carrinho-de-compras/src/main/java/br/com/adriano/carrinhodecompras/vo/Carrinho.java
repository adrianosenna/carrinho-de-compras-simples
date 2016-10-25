package br.com.adriano.carrinhodecompras.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Carrinho {
	
	private Integer id;
	private Double valorTotal;
	private List<Produto> produtos;
	
	public Carrinho(){}
	
	public Carrinho(Double valorTotal, List<Produto> produtos){
		this.valorTotal = valorTotal;
		this.produtos = produtos;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
