package tracking.app;

import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;

public class TimeTrackingService {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	
	
	public TimeTrk getById(Integer id) {
		try {
			return persistence.findObject(TimeTrk.class, id);

		} finally {
			persistence.close();
		}
	}
	
	public void persist(TimeTrk track) {
		try {
			persistence.beginTransaction();
			persistence.persist(track);
			persistence.commit();
		} finally {
			persistence.close();
		}
	}

	public void remove(TimeTrk track) {
		try {
			persistence.beginTransaction();
			
			track = persistence.findObject(TimeTrk.class, track.getId());
			
			persistence.remove(track);
			persistence.commit();
		} finally {
			persistence.close();
		}
	}
	
	public List<TimeTrk> getAll() {
		try {
			return persistence.findAll(TimeTrk.class, null);

		} finally {
			persistence.close();
		}
	}
	
}
