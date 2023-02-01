package pdv.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Date dataVenda;
	
	@OneToMany(mappedBy = "venda")
	private List<ItemVenda>carrinho = new ArrayList<ItemVenda>();
	
	@ManyToOne
	@JoinColumn(name = "id_forma_pagto")
	private FormaPagamento formaPagto;
	
	
	@Transient
	private List<ItemVenda> itens = new ArrayList<>();
	
	
	public Venda() {
	}

	public void initCarrinho() {
		itens.addAll(getCarrinho());
	}
	
	public List<ItemVenda> getCarrinhoCompras() {
		return itens;
	}

	public Double getTotalVendas() {
		List<ItemVenda> itens = getCarrinho(); 
		Double total 		  = 0D;
		
		for (ItemVenda itemVenda : itens) {
			total += itemVenda.getValorTotal();
		}
		return total;
	}
	
	public Double getValorTotal() {
		List<ItemVenda> itens = getCarrinhoCompras(); 
		Double total 		  = 0D;
		
		for (ItemVenda itemVenda : itens) {
			total += itemVenda.getValorTotal();
		}
		return total;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDataVenda() {
		return dataVenda;
	}


	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}


	public List<ItemVenda> getCarrinho() {
		return carrinho;
	}


	public void setCarrinho(List<ItemVenda> carrinho) {
		this.carrinho = carrinho;
	}

	public FormaPagamento getFormaPagto() {
		return formaPagto;
	}

	public void setFormaPagto(FormaPagamento formaPagto) {
		this.formaPagto = formaPagto;
	}

	
}
