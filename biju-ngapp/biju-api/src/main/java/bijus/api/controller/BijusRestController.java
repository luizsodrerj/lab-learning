package bijus.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bijus.entity.Peca;
import bijus.service.BijusService;
import bijus.vo.PecaVO;
import bijus.vo.PecasVO;

@Path("/bijus")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BijusRestController {

	private BijusService bijusService = new BijusService(); 
	
	
	@GET
	@Path("/get-all-bijus")
	public PecasVO getBijuterias() {
		List<? extends Peca>list = bijusService.getBijus();
		List<PecaVO>pecas		 = getPecas(list);
		
		return new PecasVO(pecas);
	}

	@GET
	@Path("/get-all-semijoias")
	public PecasVO getSemiJoias() {
		List<? extends Peca>list = bijusService.getSemiJoias();
		List<PecaVO>pecas		 = getPecas(list);
		
		return new PecasVO(pecas);
	}

	@GET
	@Path("/get-all-joias")
	public PecasVO getJoias() {
		List<? extends Peca>list = bijusService.getJoias();
		List<PecaVO>pecas		 = getPecas(list);
		
		return new PecasVO(pecas);
	}
	
	private List<PecaVO> getPecas(List<? extends Peca>list) {
		List<PecaVO>pecas = new ArrayList<PecaVO>();
		
		for (Peca peca: list) {
			PecaVO vo = new PecaVO();
			vo.copy(peca);
			pecas.add(vo);
		}
		
		return pecas;
	}
	
}








