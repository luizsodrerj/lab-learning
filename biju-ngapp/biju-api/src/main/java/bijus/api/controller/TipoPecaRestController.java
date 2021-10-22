package bijus.api.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bijus.entity.TipoPeca;
import bijus.service.Service;
import bijus.vo.TiposPecaVO;

@Path("/tipo-peca")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoPecaRestController {

	private Service service = new Service();
	
	
	@GET
	@Path("/get-all")
	public TiposPecaVO getAll() {
		List<TipoPeca>tipos = service.findAll(TipoPeca.class,null);
		
		return new TiposPecaVO(tipos);
	}

	
}








