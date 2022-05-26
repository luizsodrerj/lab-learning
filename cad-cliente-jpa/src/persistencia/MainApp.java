package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MainApp {

	public static void main(String[] args) {
		EntityManager manager = null;
		
		try {
			EntityManagerFactory fabrica = Persistencia.getFabricaPersistencia();
			
			manager = fabrica.createEntityManager();
			
		} finally {
			manager.close();
		}
	}

}
