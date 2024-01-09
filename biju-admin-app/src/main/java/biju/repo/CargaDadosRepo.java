package biju.repo;

import java.io.Serializable;
import java.util.List;

import biju.data.dto.CategoriaDTO;
import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.domain.Categoria;

public class CargaDadosRepo implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	public List<Categoria> cargaCategorias(List<CategoriaDTO> categs) {
		PersistenceServiceUtil persistence = new PersistenceServiceUtil();
		
		try {
			persistence.beginTransaction();
			
			for (CategoriaDTO categoriaDTO : categs) {
				Categoria categoria = new Categoria();
				categoria.setNome(categoriaDTO.getNome());
				
				persistence.persist(categoria);
			}
			persistence.commit();
			
			return persistence.findAll(Categoria.class, "nome");
			
		} finally {
			persistence.close();
		} 
	}
	
}
