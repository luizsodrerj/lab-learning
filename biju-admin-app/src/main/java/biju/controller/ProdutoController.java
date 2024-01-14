package biju.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.util.SerializableSupplier;

import biju.repo.CategoriaRepo;
import biju.repo.StatusRepo;
import pdv.domain.Categoria;
import pdv.domain.Produto;
import pdv.domain.StatusProduto;

@ManagedBean(name = "produtoController")
@SessionScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PRODUTO_VIEW = "/cadastro/produto/FormProduto.xhtml";

	private CategoriaRepo categoriaRepo = new CategoriaRepo();

	private List<SelectItem>categorias  = new ArrayList<>();
	private List<SelectItem>status  	= new ArrayList<>();
	private Produto produto 			= new Produto();
	private UploadedFile image;
	private String preco;
	
	

	public String redirectToForm() {
		produto = new Produto();
		image 	= null;

		produto.setCategoria(new Categoria());
		produto.setStatusProduto(new StatusProduto());
		
		populateCategorias();
		populateStatus();
		
		return PRODUTO_VIEW;
	}

	private void populateCategorias() {
		categorias.clear();
			
		List<Categoria>list = categoriaRepo.findAllCategorias();
		
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
		
		StatusRepo repo = new StatusRepo();
		
		List<StatusProduto>list = repo.findAllStatus();
		
		for (StatusProduto status : list) {
			this.status.add(
				new SelectItem(
					status.getId().toString(), 
					status.getStatus()
				)	
			);
		}
	}
	
	public StreamedContent getImageStream() {
		if (image == null) {
			return null;
		}
		try {
			return DefaultStreamedContent.builder()
                    .contentType(image.getContentType())
                    .stream(new SerializableSupplier<InputStream>() {
        				private static final long serialVersionUID = 1L;

						@Override
        				public InputStream get() {
                            try {
                                return new ByteArrayInputStream(
                                	image.getContent()
                                );
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
        				}
        			})
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	public List<SelectItem> getCategorias() {
		return categorias;
	}

	public List<SelectItem> getStatus() {
		return status;
	}

	public String getSelectedCategoriaId() {
		return  produto.getCategoria().getId() != null ?
				produto.getCategoria().getId().toString() :
				null;
	}

	public void setSelectedCategoriaId(String selectedCategoriaId) {
		this.produto.getCategoria().setId(Integer.valueOf(selectedCategoriaId));
	}

	public void setSelectedStatusId(String statusId) {
		this.produto.getStatusProduto().setId(Integer.valueOf(statusId));
	}

	public String getSelectedStatusId() {
		return  produto.getStatusProduto().getId() != null ?
				produto.getStatusProduto().getId().toString() :
				null;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	
	
	
}
