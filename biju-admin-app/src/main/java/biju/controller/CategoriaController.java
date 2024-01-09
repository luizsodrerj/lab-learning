package biju.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import biju.repo.CategoriaRepo;
import biju.service.CargaService;
import pdv.domain.Categoria;

@ManagedBean(name = "categoriaController")
@SessionScoped
public class CategoriaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CATEGORIA_VIEW = "/cadastro/categoria/FormCategoria.xhtml";
	
	private CargaService cargaService = new CargaService();
	
	private CategoriaRepo categoriaRepo = new CategoriaRepo();
	
	private List<Categoria>categorias = new ArrayList<>();
	private Categoria categoria 	  = new Categoria();
	private Categoria selectedCategoria;
	

	
	public String onClickBtConfirmar() {
		categoria.setId(null);
		
		categoriaRepo.persist(categoria);
		
		return getCategoriasList();
	}
	
	public void onRowSelect(SelectEvent<Categoria> event) {
		categoria = event.getObject();
	}
	 
	public void setSelectedCategoria(Categoria categoria) {
		this.selectedCategoria = categoria;
	}
	
	public Categoria getSelectedCategoria() {
		return selectedCategoria;
	}
	
	public String getCategoriasList() {
		categorias 		  = categoriaRepo.findAllCategorias();
		selectedCategoria = null;
		categoria  		  = new Categoria();
		
		return CATEGORIA_VIEW;
	}

	public void executarCargaCategorias() {
		categorias = cargaService.cargaCategorias();
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
}









