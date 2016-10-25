package br.com.adriano.carrinhodecompras.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produto {
	
	private Integer id;
	private String nome;
	private Double valorUnitario;
	private Integer quantidade;
	
	public Produto(){}
	
	public Produto(Integer id, String nome, Double valor, Integer quantidade){
		this.id = id;
		this.nome = nome;
		this.valorUnitario = valor;
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valor) {
		this.valorUnitario = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
