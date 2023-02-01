package persistence;

import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.domain.FormaPagamento;

public class FormaRepo {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();


	public FormaPagamento findByForma(String forma) {
		try {
			List<FormaPagamento>result = persistence.findByQuery(
									  "select f "
									+ "from   FormaPagamento f "
									+ "where  f.forma = ?1", 
									new Object[] {
										forma	
									}
								);
			return !result.isEmpty() ?
					result.get(0) :
					null;	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			persistence.close();
		}
	}
	
	public void update(FormaPagamento forma) {
		try {
			persistence.beginTransaction();
			
			FormaPagamento managedForma = persistence.findObject(FormaPagamento.class,forma.getId());
			managedForma.setForma(forma.getForma());
			
			persistence.merge(managedForma);
			persistence.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			persistence.rollbackTransaction();
			throw e;
		} finally {
			persistence.close();
		}
	}
	
	public List<FormaPagamento> getAll() {
		try {
			return persistence.findAll(
						FormaPagamento.class, 
						"forma"
					);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			persistence.close();
		}
	}
	
	public void persist(FormaPagamento forma) {
		try {
			persistence.beginTransaction();
			persistence.persist(forma);
			persistence.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			persistence.rollbackTransaction();
		} finally {
			persistence.close();
		}
	}
	
}
