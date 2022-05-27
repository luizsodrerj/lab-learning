package persistencia;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class MainApp {

	void inserirClientes(EntityManager manager) {
		Cliente ibm = new Cliente();
		ibm.setNomeRazaoSocial("IBM Tecnologia SA");
		ibm.setDataCadastro(new Date());
		
		Cliente havan = new Cliente();
		havan.setNomeRazaoSocial("Havan LDTA");
		havan.setDataCadastro(new Date());
		
		Cliente google = new Cliente();
		google.setNomeRazaoSocial("Google Inc");
		google.setDataCadastro(new Date());
		
		manager.getTransaction().begin();
		manager.persist(ibm);
		manager.persist(havan);
		manager.persist(google);
		manager.getTransaction().commit();
	}

	void listarClientes(EntityManager manager) {
		Query query = manager.createQuery("select c from Cliente c");
		List<Cliente>resultList = query.getResultList();
		
		for (Cliente cliente : resultList) {
			System.out.println("id - "+cliente.getId());
			System.out.println("nome - "+cliente.getNomeRazaoSocial());
			
			Date data = cliente.getDataCadastro();
			String dtFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data);
			System.out.println("data cadastro - "+dtFormatada);
			System.out.println("------");
		}
	}
	
	private void updateCliente(Cliente cliente, EntityManager manager) {
		manager.getTransaction().begin();
		
		String nome = cliente.getNomeRazaoSocial();
		Date dataCad = cliente.getDataCadastro();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataCad);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		cliente.setDataCadastro(cal.getTime());
		cliente.setNomeRazaoSocial(nome + " atualizada em: " + cliente.getDataCadastro().toString());
		manager.merge(cliente);
		
		manager.getTransaction().commit();
	}
	
	private Cliente buscarPorId(EntityManager manager, Integer id) {
		Cliente cliente = manager.find(Cliente.class, id);
		
		System.out.println("-------------------");
		System.out.println("id - "+cliente.getId());
		System.out.println("nome - "+cliente.getNomeRazaoSocial());
		
		Date data = cliente.getDataCadastro();
		String dtFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data);
		System.out.println("data cadastro - "+dtFormatada);
		
		return cliente;
	}
	
	public static void main(String[] args) {
		EntityManager manager = null;
		MainApp app = new MainApp();
		
		try {
			EntityManagerFactory fabrica = Persistencia.getFabricaPersistencia();
			
			manager = fabrica.createEntityManager();

			//app.inserirClientes(manager);
			app.listarClientes(manager);
			
			app.buscarPorId(manager, 2);
			Cliente cliente = app.buscarPorId(manager, 1);
			
			app.updateCliente(cliente, manager);
			
			cliente = app.buscarPorId(manager, 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

}
