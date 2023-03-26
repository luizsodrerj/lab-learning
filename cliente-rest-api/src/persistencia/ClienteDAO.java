package persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class ClienteDAO {

	
	public void alterarCliente(
					String nomeRazao,
					Date dataCadastro,
					Integer idCliente,
					Integer idMatriz
				) {
		EntityManager manager = null;
		try {
			manager  = Persistencia.getFabricaPersistencia().createEntityManager();
			manager.getTransaction().begin();
			
			Cliente cliente = manager.find(Cliente.class, idCliente);
			
			cliente.setNomeRazaoSocial(nomeRazao);
			cliente.setDataCadastro(dataCadastro);
			
			if (idMatriz != null) {
				Cliente matriz = manager.find(Cliente.class, Integer.valueOf(idMatriz));
				matriz.getFiliais().add(cliente);
				cliente.setMatriz(matriz);
			}
			manager.merge(cliente);
			manager.getTransaction().commit();
			
		} finally {
			manager.close();
		}
	}
	
	public List<Cliente> consultarFilial(
							String nomeRazao,
							Date dataCadastro,
							Integer idMatriz
						 ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();

		jpaql.append("select filial from Cliente filial ");
		jpaql.append("where filial.matriz is not null ");

		if (nomeRazao != null) {
			jpaql.append("and upper(filial.nomeRazaoSocial) like :nomeRazaoSocial ");
			params.put("nomeRazaoSocial", "%" + nomeRazao.toUpperCase() + "%");
		}
		if (dataCadastro != null) {
			jpaql.append("and filial.dataCadastro = :dataCadastro ");
			params.put("dataCadastro", dataCadastro);
		}
		if (idMatriz != null) {
			jpaql.append("and filial.matriz.id = :idMatriz");
			params.put("idMatriz", idMatriz);
		}

		EntityManager manager = null;
		try {
			manager = Persistencia.getFabricaPersistencia().createEntityManager();
			Query query = manager.createQuery(jpaql.toString());

			Set<String> nomesParametros = params.keySet();

			for (String nomeParametro : nomesParametros) {
				query.setParameter(nomeParametro, params.get(nomeParametro));
			}

			return query.getResultList();

		} finally {
			manager.close();
		}
	}
	
	public List<Cliente> consultarMatriz(
							String nomeRazao,
							Date dataCadastro,
							String nomeFilial
						 ) {
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuilder jpaql = new StringBuilder();
		
		jpaql.append("select distinct matriz from Cliente matriz ");
		
		if (nomeFilial != null) {
			jpaql.append("join matriz.filiais f ");
		}
		jpaql.append("where matriz.matriz is null ");
		
		if (nomeRazao != null) {
			jpaql.append("and upper(matriz.nomeRazaoSocial) like :nomeRazaoSocial ");
			params.put("nomeRazaoSocial", "%"+nomeRazao.toUpperCase()+"%");
		}
		if (dataCadastro != null) {
			jpaql.append("and matriz.dataCadastro = :dataCadastro ");
			params.put("dataCadastro", dataCadastro);
		}
		if (nomeFilial != null) {
			jpaql.append("and upper(f.nomeRazaoSocial) like :nomeFilial ");
			params.put("nomeFilial", "%"+nomeFilial.toUpperCase()+"%");
		}
		
		EntityManager manager = null;
		try {
			manager  = Persistencia.getFabricaPersistencia().createEntityManager();
			Query query = manager.createQuery(jpaql.toString());
			
			Set<String>nomesParametros = params.keySet();
			
			for (String nomeParametro: nomesParametros) {
				query.setParameter(nomeParametro, params.get(nomeParametro));
			}
			
			return query.getResultList();
			
		} finally {
			manager.close();
		}
	}
	
	public List<Cliente> ordenarPorNome() {
		return selecionarTodos(
					"select c from Cliente c "
				  + "order by c.nomeRazaoSocial"
				);
	}

	public List<Cliente> ordenarPorMatriz() {
		return selecionarTodos(
					"select c from Cliente c "
				  + "order by c.matriz.nomeRazaoSocial"
				);
	}

	public List<Cliente> ordenarPorDataCadastro() {
		return selecionarTodos(
					"select c from Cliente c "
				  + "order by c.dataCadastro"
				);
	}
	
	public List<Cliente> listarTodos() {
		return selecionarTodos(
					"select c from Cliente c"
				);
	}

	public Cliente findMatriz(Integer idMatriz) {
		EntityManager manager = null;
		try {
			manager  = Persistencia.getFabricaPersistencia().createEntityManager();
			Cliente matriz =  manager.find(Cliente.class, idMatriz);
			List<Cliente>filiais = new ArrayList<Cliente>();
			
			filiais.addAll(matriz.getFiliais());
			matriz.setFiliais(filiais);
			
			return matriz;
			
		} finally {
			manager.close();
		}
	}
	
	public Cliente find(Integer idCliente) {
		EntityManager manager = null;
		try {
			manager  = Persistencia.getFabricaPersistencia().createEntityManager();
			return   manager.find(Cliente.class, idCliente);
		} finally {
			manager.close();
		}
	}
	
	public List<Cliente> selecionarTodos(String jPAQL) {
		EntityManager manager = null;
		try {
			EntityManagerFactory fact = Persistencia.getFabricaPersistencia();
			manager  = fact.createEntityManager();
			return   manager.createQuery(jPAQL)
						    .getResultList(); 
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close();
		}
	}
	
	public List<Cliente> selecionarMatrizes() {
		EntityManager manager = null;
		try {
			manager = Persistencia.getFabricaPersistencia().createEntityManager();
			
			return manager.createQuery(
						"select c from Cliente c "
					  + "where c.matriz is null"
					)
					.getResultList();
		} finally {
			manager.close();
		}
	}
	
	public Cliente cadastrarCliente(String nomeRazao,Date dataCadastro,String idMatriz) {
		EntityManager manager = null;
		try {
			manager  = Persistencia.getFabricaPersistencia().createEntityManager();
			manager.getTransaction().begin();
			
			Cliente cliente = new Cliente();
			cliente.setNomeRazaoSocial(nomeRazao);
			cliente.setDataCadastro(dataCadastro);
			
			if (idMatriz != null) {
				Cliente matriz = manager.find(Cliente.class, Integer.valueOf(idMatriz));
				matriz.getFiliais().add(cliente);
				cliente.setMatriz(matriz);
			}
			manager.persist(cliente);
			manager.getTransaction().commit();
			
			return cliente;
			
		} finally {
			manager.close();
		}
	}
	
	
}
