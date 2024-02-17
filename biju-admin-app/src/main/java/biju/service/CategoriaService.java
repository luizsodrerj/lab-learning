package biju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import biju.repo.CategoriaJPARepository;
import pdv.domain.Categoria;

@Component
public class CategoriaService {

	@Autowired
	private CategoriaJPARepository categoriaJPARepository;

	
	public void update(Categoria categoria) {
		Categoria cat = categoriaJPARepository.findOne(categoria.getId()); 
		cat.setNome(categoria.getNome());
		
		categoriaJPARepository.save(cat);
	}
	
}
