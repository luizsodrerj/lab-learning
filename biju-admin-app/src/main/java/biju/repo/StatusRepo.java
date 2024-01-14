package biju.repo;

import java.io.Serializable;
import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.domain.StatusProduto;

public class StatusRepo implements Serializable {

	private static final long serialVersionUID = 1L;

	
	public List<StatusProduto> findAllStatus() {
		PersistenceServiceUtil persistence = new PersistenceServiceUtil();
		
		try {
			return persistence.findAll(StatusProduto.class, null);
			
		} finally {
			persistence.close();
		} 
	}

	
}
