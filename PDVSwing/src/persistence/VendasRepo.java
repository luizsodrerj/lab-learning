package persistence;

import java.util.Date;
import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.domain.ItemVenda;
import pdv.domain.Produto;
import pdv.domain.TotalVendasAno;
import pdv.domain.TotalVendasMesAno;
import pdv.domain.TotalVendasPorData;
import pdv.domain.Venda;

public class VendasRepo {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	

	public List<TotalVendasPorData> getConsolidadoByData(Date data) {
		try {
			return persistence.findByQuery(
						  "select v "
						+ "from   TotalVendasPorData v "
						+ "where  v.dataVenda = ?1 ",
						new Object[] {
							data	
						}
					);
		} finally {
			persistence.close();
		}
	}
	
	public List<TotalVendasAno> getConsolidadoByAno(int ano) {
		try {
			return persistence.findByQuery(
					  "select   v 	"
					+ "from 	TotalVendasAno v "
					+ "where	v.ano = ?1 ",
						new Object[] {
							ano	
						}
					);
		} finally {
			persistence.close();
		}
	}
	
	public List<TotalVendasPorData> getConsolidadoByPeriodo(Date ini, Date fim) {
		try {
			return persistence.findByQuery(
						  "select v "
						+ "from   TotalVendasPorData v "
						+ "where  v.dataVenda >= ?1 "
						+ "and	  v.dataVenda <= ?2 ",
						new Object[] {
							ini, fim	
						}
					);
		} finally {
			persistence.close();
		}
	}
	
	public List<TotalVendasMesAno> getConsolidadoByMesAno(int mes, int ano) {
		try {
			String mesVenda = mes < 10 ? "0" + mes : String.valueOf(mes);
			String anoMes 	= ano + mesVenda; 
			
			return persistence.findByQuery(
					  "select   v 		"
					+ "from 	TotalVendasMesAno v	"
					+ "where	v.anoMes = ?1 ",
						new Object[] {
							anoMes	
						}
					);
		} finally {
			persistence.close();
		}
	}
	
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
				if (item.getProduto() != null && item.getProduto().getId() != null) {
					Integer idProd  = item.getProduto().getId();
					Produto produto = persistence.findObject(Produto.class, idProd);
					item.setProduto(produto);
				}
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
