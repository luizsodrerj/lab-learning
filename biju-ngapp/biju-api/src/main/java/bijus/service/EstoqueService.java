package bijus.service;

import java.util.List;

import bijus.entity.Peca;
import framework.persistence.jpa.PersistenceServiceUtil;

public class EstoqueService extends BaseService {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	
	
	public List<Peca> getBijus() {
		try {
			return persistence.findByNamedQuery("Peca.bijuterias", null);
		} finally {
			persistence.close();
		}
	}

	public List<Peca> getSemiJoias() {
		try {
			return persistence.findByNamedQuery("Peca.semijoias", null);
		} finally {
			persistence.close();
		}
	}

	public List<Peca> getJoias() {
		try {
			return persistence.findByNamedQuery("Peca.joias", null);
		} finally {
			persistence.close();
		}
	}
	
}






