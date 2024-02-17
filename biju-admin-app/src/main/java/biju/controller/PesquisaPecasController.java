package biju.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import biju.repo.CategoriaJPARepository;
import biju.repo.StatusJPARepository;
import biju.service.ProdutoService;
import biju.util.FacesUtil;
import pdv.domain.Categoria;
import pdv.domain.Produto;
import pdv.domain.StatusProduto;

@Component("pesqPecasController") 
@Scope("session") 
public class PesquisaPecasController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PESQ_VIEW  = "/produtos/ParamsPesquisa.xhtml";

	private List<Produto>produtos		= new ArrayList<>();
	private List<SelectItem>categorias  = new ArrayList<>();
	private List<SelectItem>status  	= new ArrayList<>();
	private StatusProduto statusProd	= new StatusProduto(); 				
	private Categoria categoria			= new Categoria();
	private Produto produto 			= new Produto();
	
	private String statusResultPanel	= "";
	private String statusFilterPanel	= "";

	@Autowired
	private CategoriaJPARepository categoriaJPARepository;
	
	@Autowired
	private StatusJPARepository statusJPARepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	
	
	public String onClickBtPesquisar() {
		produto.setStatusProduto(statusProd);
		produto.setCategoria(categoria);
		
		produtos = produtoService.findProducts(produto);

		statusFilterPanel = "hidden";
		statusResultPanel = "visible";

		HttpSession session = FacesUtil.getSession();
		session.setAttribute("produtos", produtos);
		
		return null;
	}

	public String redirectToForm() {
		reset();

		categoria	= new Categoria();
		produto 	= new Produto();
		
		produto.setCategoria(categoria);
		produto.setStatusProduto(statusProd);
		
		populateCategorias();
		populateStatus();
		
		return PESQ_VIEW;
	}

	private void reset() {
		statusFilterPanel = "visible";
		statusResultPanel = "hidden";
		
		produto = new Produto();

		HttpSession session = FacesUtil.getSession();
		Object sessionList  = session.getAttribute("produtos");
		
		if (sessionList != null) {
			session.removeAttribute("produtos");	
		}
	}
	
	private void populateCategorias() {
		categorias.clear();
			
		List<Categoria>list = categoriaJPARepository.findAllByOrderByNomeAsc();
		
		for (Categoria categoria : list) {
			categorias.add(
				new SelectItem(
					categoria.getId().toString(), 
					categoria.getNome()
				)	
			);
		}
	}

	private void populateStatus() {
		this.status.clear();
		
		List<StatusProduto>list = statusJPARepository.findAll();
		
		for (StatusProduto status : list) {
			this.status.add(
				new SelectItem(
					status.getId().toString(), 
					status.getStatus()
				)	
			);
		}
	}

	public List<SelectItem> getCategorias() {
		if (categorias.isEmpty()) {
			populateCategorias();
		}
		return categorias;
	}

	public List<SelectItem> getStatus() {
		if (status.isEmpty()) {
			populateStatus();
		}
		return status;
	}

	public String getSelectedCategoriaId() {
		return  categoria.getId() != null ?
				categoria.getId().toString() :
				null;
	}

	public void setSelectedCategoriaId(String selectedCategoriaId) {
		this.categoria.setId(
			StringUtils.isNotBlank(selectedCategoriaId) ?	
			Integer.valueOf(selectedCategoriaId) : 
			null	
		);
	}

	public void setSelectedStatusId(String statusId) {
		this.statusProd.setId(
			StringUtils.isNotBlank(statusId) ?	
			Integer.valueOf(statusId) :
			null	
		);
	}

	public String getSelectedStatusId() {
		return  statusProd.getId() != null ?
				statusProd.getId().toString() :
				null;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public StatusProduto getStatusProd() {
		return statusProd;
	}

	public void setStatusProd(StatusProduto statusProd) {
		this.statusProd = statusProd;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getStatusResultPanel() {
		return statusResultPanel;
	}

	public String getStatusFilterPanel() {
		return statusFilterPanel;
	}	
	
	
}
