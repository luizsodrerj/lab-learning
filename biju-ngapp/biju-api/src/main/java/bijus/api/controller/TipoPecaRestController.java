package bijus.api.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bijus.entity.TipoPeca;
import bijus.vo.TiposPecaVO;
import framework.persistence.jpa.PersistenceServiceUtil;

@Path("/tipo-peca")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoPecaRestController {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	
	@GET
	@Path("/get-all")
	public TiposPecaVO getAll() {
		try {
			List<TipoPeca>tipos = persistence.findAll(TipoPeca.class,null);
			
			return new TiposPecaVO(tipos);
			
		} finally {
			persistence.close();
		}
	}

	
}








