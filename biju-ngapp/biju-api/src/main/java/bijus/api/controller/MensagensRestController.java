package bijus.api.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bijus.entity.Mensagem;
import bijus.entity.Mensagens;
import bijus.service.MensagensService;

@Path("/admin-msg")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MensagensRestController {

	private MensagensService service = new MensagensService();
	
	
	@PUT
	@Path("/add-msg")
	public Mensagem addMesg(Mensagem msg) {
		msg.setStatus(Mensagem.MessageStatus.NOVA.getId());
		msg.setData(new Date());

		service.persist(msg);
		
		return msg;
	}
	
	@GET
	@Path("/get-all")
	public Mensagens getMensagens() {
		List<Mensagem>list  = service.findAll(Mensagem.class, null);
		
		return new Mensagens(list);
	}
	
}





