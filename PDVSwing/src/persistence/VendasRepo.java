package persistence;

import java.util.Date;
import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.ItemVenda;
import pdv.Produto;
import pdv.Venda;

public class VendasRepo {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	

	public List<Venda> findByMesAno(int mes, int ano) {
		try {
			return persistence.findByQuery(
					  "select   v 		"
					+ "from 	Venda v	"
					+ "where	month(v.dataVenda) = ?1 "
					+ "and		year(v.dataVenda)  = ?2 "
					+ "and exists ( "
					+ "		select i from ItemVenda i "
					+ "		where  i.venda.id = v.id  "
					+ ")",
						new Object[] {
							mes, ano	
						}
					);
		} finally {
			persistence.close();
		}
	}
	
	public List<Venda> findByPeriodo(Date ini, Date fim) {
		try {
			return persistence.findByQuery(
						 "select v from Venda v "
						+ "where v.dataVenda >= ?1 "
						+ "and	 v.dataVenda <= ?2 "
						+ "and exists ( "
						+ "		select i from ItemVenda i "
						+ "		where  i.venda.id = v.id  "
						+ ")",
						new Object[] {
							ini, fim	
						}
					);
		} finally {
			persistence.close();
		}
	}
	
	public Venda getVenda(Integer id) {
		try {
			Venda venda = persistence.findObject(
								Venda.class, 
								id
							);
			venda.initCarrinho();
			
			return venda;
			
		} finally {
			persistence.close();
		}
	}
	
	public List<Venda> getVendas() {
		try {
			return persistence.findByQuery(
					  "select v from Venda v "
					+ "where exists ( "
					+ "		select i from ItemVenda i "
					+ "		where  i.venda.id = v.id  "
					+ ")",
					null
				);
		} finally {
			persistence.close();
		}
	}
	
	public void persistVenda(Venda venda) {
		try {
			persistence.beginTransaction();
			
			List<ItemVenda>carrinho = venda.getCarrinho();
			persistence.persist(venda);
			
			for (ItemVenda item: carrinho) {
				Integer idProd  = item.getProduto().getId();
				Produto produto = persistence.findObject(Produto.class, idProd);
				item.setProduto(produto);
				item.setVenda(venda);
				persistence.persist(item);
			}
			persistence.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			persistence.rollbackTransaction();
			throw e;
		} finally {
			persistence.close();
		}
	}
}
