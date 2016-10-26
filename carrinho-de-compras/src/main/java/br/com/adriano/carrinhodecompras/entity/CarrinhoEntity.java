package br.com.adriano.carrinhodecompras.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe entidade de persistencia para carrinho de compras
 * @author Adriano
 */
@Entity
@Table(name="carrinho")
public class CarrinhoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produto", nullable = false)
	private ProdutoEntity produtoEntity;
	
	@Column(name="qtd_produto")
	private Integer quantidade;
	
	@Column
	private String status;

	public ProdutoEntity getProdutoEntity() {
		return produtoEntity;
	}

	public void setProdutoEntity(ProdutoEntity produtoEntity) {
		this.produtoEntity = produtoEntity;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
