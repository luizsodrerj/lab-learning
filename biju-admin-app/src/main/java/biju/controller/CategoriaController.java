package biju.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import biju.repo.CategoriaJPARepository;
import biju.service.CargaService;
import biju.service.CategoriaService;
import pdv.domain.Categoria;

//@ManagedBean(name = "categoriaController")
//@SessionScoped
@Component("categoriaController") 
@Scope("session") 
public class CategoriaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CATEGORIA_VIEW = "/cadastro/categoria/FormCategoria.xhtml";
	
	private CargaService cargaService = new CargaService();
	
	private List<Categoria>categorias = new ArrayList<>();
	private Categoria categoria 	  = new Categoria();
	private Categoria selectedCategoria;
	
	@Autowired
	private CategoriaJPARepository categoriaJPARepository;

	@Autowired
	private CategoriaService categoriaService;
	
	
	public String onClickBtConfirmar() {
		if (selectedCategoria != null) {
			categoria.setId(selectedCategoria.getId());
			categoriaService.update(categoria);
		} else {
			categoria.setId(null);
			categoriaJPARepository.save(categoria);
		}
		
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
		categorias 		  = categoriaJPARepository.findAllByOrderByNomeAsc();
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









