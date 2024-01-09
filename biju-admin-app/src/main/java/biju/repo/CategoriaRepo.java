package biju.repo;

import java.io.Serializable;
import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.domain.Categoria;

public class CategoriaRepo implements Serializable {

	private static final long serialVersionUID = 1L;
	
		

	
	public Categoria getById(Categoria categoria) {
		PersistenceServiceUtil persistence = new PersistenceServiceUtil();
		
		try {
			return persistence.findObject(Categoria.class, categoria.getId());
			
		} finally {
			persistence.close();
		} 
	}

	public void update(Categoria categoria) {
		PersistenceServiceUtil persistence = new PersistenceServiceUtil();
		
		try {
			persistence.beginTransaction();
			
			Categoria cat = persistence.findObject(Categoria.class, categoria.getId());
			cat.setNome(categoria.getNome());
			
			persistence.merge(categoria);
			persistence.commit();
			
		} finally {
			persistence.close();
		} 
	}

	public void persist(Categoria categoria) {
		PersistenceServiceUtil persistence = new PersistenceServiceUtil();
		
		try {
			persistence.beginTransaction();
			persistence.persist(categoria);
			persistence.commit();
			
		} finally {
			persistence.close();
		} 
	}
	
	public List<Categoria> findAllCategorias() {
		PersistenceServiceUtil persistence = new PersistenceServiceUtil();
		
		try {
			return persistence.findAll(Categoria.class, "nome");
			
		} finally {
			persistence.close();
		} 
	}
	
}
