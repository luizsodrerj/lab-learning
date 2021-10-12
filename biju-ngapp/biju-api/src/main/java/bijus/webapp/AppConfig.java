package bijus.webapp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import bijus.api.controller.MensagensRestController;

//@ApplicationPath("/api")
public class AppConfig extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	
	
	public AppConfig() {
		singletons.add(new MensagensRestController());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
