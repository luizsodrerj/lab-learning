package pdv;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityFactory;
	
	static {
		entityFactory = Persistence.createEntityManagerFactory("PDVPU");
	}
	
	public static EntityManager getEntityManager() {
		return entityFactory.createEntityManager();
	}
	
}
