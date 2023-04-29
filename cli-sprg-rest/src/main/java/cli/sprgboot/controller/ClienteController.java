package cli.sprgboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cli.sprgboot.entity.Cliente;
import cli.sprgboot.repo.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> all() {
		return new ResponseEntity<List<Cliente>>(
			repository.findAll(),
			HttpStatus.OK
		);
	}

}
