package biju.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import biju.service.ProdutoService;
import biju.util.FacesUtil;
import pdv.domain.Produto;

@Component("pecasListController") 
@Scope("request") 
public class PecasListController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produto>produtos = new ArrayList<>();

	@Autowired
	private ProdutoService produtoService;

	
	@PostConstruct
	private void initialize() {
		produtos = produtoService.getProductList();
		
		FacesUtil.getSession()
				 .setAttribute(
					"produtos", 
					produtos
				);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
	
	
}




