package biju.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import biju.repo.CategoriaJPARepository;
import biju.repo.ProdutoJPARepository;
import biju.repo.StatusJPARepository;
import pdv.domain.Categoria;
import pdv.domain.Produto;
import pdv.domain.StatusProduto;

@Component
public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoJPARepository produtoJPARepository;

	@Autowired
	private CategoriaJPARepository categoriaJPARepository;

	@Autowired
	private StatusJPARepository statusJPARepository; 

	

	public void updateProduto(Produto produto) {
		try {
			StatusProduto status =	produto.getStatusProduto() != null &&
									produto.getStatusProduto().getId() != null ?
									statusJPARepository.findOne(produto.getStatusProduto().getId()) :		
									null;
			Categoria cat = produto.getCategoria() != null &&
							produto.getCategoria().getId() != null ?
							categoriaJPARepository.findOne(produto.getCategoria().getId()) :
							null;	
	
			Produto managedProduto = produtoJPARepository.findOne(produto.getId());
			
			managedProduto.setStatusProduto(status);
			managedProduto.setCategoria(cat);
			managedProduto.setDescricao(produto.getDescricao());
			managedProduto.setQtdEstoque(produto.getQtdEstoque());
			managedProduto.setImagem(produto.getImagem());
			managedProduto.setNome(produto.getNome());
			managedProduto.setPreco(produto.getPreco());
			
			produtoJPARepository.save(produto);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}
	
	public void persistProduto(Produto produto) {
		try {
			StatusProduto status =	produto.getStatusProduto() != null &&
									produto.getStatusProduto().getId() != null ?
									statusJPARepository.findOne(produto.getStatusProduto().getId()) :		
									null;
			Categoria cat = produto.getCategoria() != null &&
							produto.getCategoria().getId() != null ?
							categoriaJPARepository.findOne(produto.getCategoria().getId()) :
							null;	
	
			produto.setStatusProduto(status);
			produto.setCategoria(cat);
			produto.setId(null);

			produtoJPARepository.save(produto);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}

	public List<Produto> findProducts(Produto produto) {
		return produtoJPARepository.findProducts(produto);
	}

	public List<Produto> getProductList() {
		return produtoJPARepository.findTop12ByOrderByIdDesc();
	}
	
//	public void persistProduto(Produto produto) {
//		try {
//			persistence.beginTransaction();
//
//			StatusProduto status =	produto.getStatusProduto() != null &&
//									produto.getStatusProduto().getId() != null ?
//									persistence.findObject(StatusProduto.class, produto.getStatusProduto().getId()) :		
//									null;
//			Categoria cat = produto.getCategoria() != null &&
//							produto.getCategoria().getId() != null ? 
//							persistence.findObject(Categoria.class, produto.getCategoria().getId()) :
//							null;	
//
//			produto.setStatusProduto(status);
//			produto.setCategoria(cat);
//			produto.setId(null);
//			
//			persistence.persist(produto);
//			persistence.commit();
//			
//		} catch (Exception e) {
//			persistence.rollbackTransaction();
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} finally {
//			persistence.close();
//		}
//	}
	
	
}






