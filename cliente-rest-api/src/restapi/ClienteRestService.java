package restapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import persistencia.Cliente;
import persistencia.ClienteDAO;

@Path("/cliente")
public class ClienteRestService {

	private ClienteDAO clienteDAO = new ClienteDAO();
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public ClienteValue cadastrarCliente(ClienteValue clienteValue) {
		String nomeRazaoSocial = clienteValue.getNomeRazaoSocial();
		String idMatriz = clienteValue.getIdMatriz();
		Date dataCadastro = null;
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataCadastro = df.parse(clienteValue.getDataCadastro());
		} catch (ParseException e) {
		}
		Cliente cliente = clienteDAO.cadastrarCliente(
								nomeRazaoSocial, 
								dataCadastro, 
								idMatriz
							);
		ClienteValue valueObject = getClienteValue(df, cliente);
				
		return valueObject;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar-matrizes")
	public List<ClienteValue> getListMatrizes() {
		List<ClienteValue>matrizes = new ArrayList<ClienteValue>();
		List<Cliente>list = clienteDAO.selecionarMatrizes();

		for (Cliente cliente : list) {
			ClienteValue valueObject = new ClienteValue();
			valueObject.setNomeRazaoSocial(cliente.getNomeRazaoSocial());
			valueObject.setId(cliente.getId().toString());

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			valueObject.setDataCadastro(df.format(cliente.getDataCadastro()));
			
			matrizes.add(valueObject);
		}
		return matrizes;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar")
	public List<ClienteValue> getList() {
		List<ClienteValue> clientes = new ArrayList<ClienteValue>();
		List<Cliente> list = clienteDAO.listarTodos();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		for (Cliente cliente : list) {
			ClienteValue valueObject = getClienteValue(df, cliente);
			clientes.add(valueObject);
		}

		return clientes;
	}

	private ClienteValue getClienteValue(SimpleDateFormat df, Cliente cliente) {
		ClienteValue valueObject = new ClienteValue();
		valueObject.setNomeRazaoSocial(cliente.getNomeRazaoSocial());
		valueObject.setId(cliente.getId().toString());
		valueObject.setDataCadastro(df.format(cliente.getDataCadastro()));

		valueObject.setNomeMatriz(
			cliente.getMatriz() != null ? 
			cliente.getMatriz().getNomeRazaoSocial() : 
			null
		);
		return valueObject;
	}
	
}






