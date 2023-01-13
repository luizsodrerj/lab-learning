package persistence;

import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.ItemVenda;
import pdv.Produto;
import pdv.Venda;

public class VendasRepo {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	
	
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
