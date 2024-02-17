package biju.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import pdv.domain.Categoria;
import pdv.domain.Produto;
import pdv.domain.StatusProduto;

public class ProdServMock implements Serializable {

	private static final long serialVersionUID = 1L;

	
	public List<Produto> getListMock() {
		List<Produto>list = new ArrayList<>();

		try {
			Produto p1 =	new Produto(
					"Pulseira de Teste", 
					"Pulseira", 
					15.00, 
					null,
					IOUtils.toByteArray(
						new FileInputStream(
							new File(
							  "C:\\java\\bkp\\mock\\pul-ver.jpeg"
							)
						)
					),
					new StatusProduto(1, "Disponivel"),
					new Categoria(null, "Prata")
				);
			p1.setId(0);
			list.add(p1);
			
			for (int i = 0; i < 10; i++) {
				Produto p = new Produto(
						"Pulseira de Teste", 
						"Pulseira", 
						15.00, 
						null,
						IOUtils.toByteArray(
							new FileInputStream(
								new File(
								  "C:\\java\\bkp\\mock\\pulseira-3-pin-prata.PNG"
								)
							)
						),
						new StatusProduto(null, "Esgotado"),
						new Categoria(null, "Prata")
					);
				p.setId(i + 1);
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return list;
	}
	
	
}
