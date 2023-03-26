package persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Persistencia {

	private static EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("ClientePU"); 
	
	
	public static EntityManagerFactory getFabricaPersistencia() {
		if (entityFactory == null) {
			System.out.println(entityFactory);
		}
		
		return entityFactory;
	}
}
