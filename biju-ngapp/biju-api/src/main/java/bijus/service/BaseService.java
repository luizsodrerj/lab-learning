package bijus.service;

import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;

public abstract class BaseService {

	private PersistenceServiceUtil persis = new PersistenceServiceUtil();

	
	public void persist(Object entity) {
		try {
			persis.beginTransaction();
			persis.persist(entity);
			persis.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			persis.rollbackTransaction();
			throw new RuntimeException(e);
		} finally {
			persis.close();
		}
	}
	
	public <T>T findObject(Class<T> classe, Object id) {
		try {
			return persis.findObject(classe, id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			persis.close();
		}
	}
	
	
	public <T> List<T> findAll(Class<T> type, String orderBy) {
		try {
			return persis.findAll(type, orderBy);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			persis.close();
		}
	}
	
}
