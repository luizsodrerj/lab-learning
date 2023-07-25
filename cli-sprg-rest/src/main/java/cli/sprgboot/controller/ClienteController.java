package cli.sprgboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cli.sprgboot.entity.Cliente;
import cli.sprgboot.entity.ClienteVO;
import cli.sprgboot.repo.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository repository;
	

	
	@RequestMapping(value = "/create-client", method = RequestMethod.POST)
	public ResponseEntity<Cliente> create(@RequestBody ClienteVO vo) {
		Cliente client  = new Cliente();
		try {
			client.setDataCadastro(
				new SimpleDateFormat("dd/MM/yyyy").parse(vo.getDataCadastro())
			);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		client.setNomeRazaoSocial(vo.getNomeRazaoSocial());
		
		if (vo.getIdMatriz() != null) {
			Cliente matriz = repository.findById(Integer.valueOf(vo.getIdMatriz())).get();
			client.setMatriz(matriz);
		}
		client = repository.save(client);
		
		return new ResponseEntity<Cliente>(
			client, HttpStatus.OK
		);
	}
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ResponseEntity<List<ClienteVO>> all() {
		List<ClienteVO>list = new ArrayList<ClienteVO>();
		List<Cliente>result = repository.findAll();
		
		for (Cliente cliente : result) {
			ClienteVO vo = new ClienteVO();
			vo.setNomeRazaoSocial(cliente.getNomeRazaoSocial());
			vo.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataCadastro()));
			vo.setId(cliente.getId().toString());
			
			if (cliente.getMatriz() != null) {
				vo.setNomeMatriz(cliente.getMatriz().getNomeRazaoSocial());
				vo.setIdMatriz(cliente.getMatriz().getId().toString());
			}
			list.add(vo);
		}
		
		return new ResponseEntity<List<ClienteVO>>(
			list, HttpStatus.OK
		);
	}

}









