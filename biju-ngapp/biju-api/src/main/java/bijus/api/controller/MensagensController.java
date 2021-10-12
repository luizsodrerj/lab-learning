package bijus.api.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bijus.entity.Mensagem;
import bijus.service.MensagensService;

@Path("/admin-msg")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class MensagensController {

	private MensagensService service = new MensagensService();
	
	@GET
	@Path("/allmsg")
	public List<Mensagem> getMensagens() {
		return service.findAll(Mensagem.class, null);
	}
	
}
