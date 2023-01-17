package persistence;

import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.Produto;

public class ProdutosRepo {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();

	
	
	public void updateProduto(Produto produto) {
		try {
			persistence.beginTransaction();
			
			Produto managedProd = persistence.findObject(Produto.class,produto.getId());
			managedProd.setCodigo(produto.getCodigo());
			managedProd.setNome(produto.getNome());
			managedProd.setPreco(produto.getPreco());
			
			persistence.merge(managedProd);
			persistence.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			persistence.rollbackTransaction();
			throw e;
		} finally {
			persistence.close();
		}
	}
	
	public void insertProduto(Produto produto) {
		try {
			persistence.beginTransaction();
			persistence.persist(produto);
			persistence.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			persistence.rollbackTransaction();
			throw e;
		} finally {
			persistence.close();
		}
	}

	public Produto findProdutoByNomeOuCodigo(String nomeOuCodigo) {
		try {
			List<Produto>result = persistence.findByQuery(
				  "select p from Produto p "
				+ "where ( "
				+ "	upper(p.nome) like upper(?1) "
				+ " or   p.codigo like ?2 "
				+ ")"
				+ "order by p.nome", 
				new Object[] {
					"%"	+ nomeOuCodigo + "%",
					"%"	+ nomeOuCodigo + "%"
				}
			);
			return !result.isEmpty() ?
					result.get(0) :
					null;
					  
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			persistence.close();
		}
	}
	
	public List<Produto> findProduto(String nome) {
		try {
			return persistence.findByQuery(
						  "select p from Produto p "
						+ "where upper(p.nome) like upper(?1) "
						+ "order by p.nome", 
						new Object[] {
							"%"	+ nome + "%"
						}
					);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			persistence.close();
		}
	}
	
	public List<Produto> getProdutos() {
		try {
			return persistence.findByQuery(
						"select p from Produto p order by p.nome", 
						null
					);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			persistence.close();
		}
	}
	
}







