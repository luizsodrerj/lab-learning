package biju.repo;

import java.util.List;

import pdv.domain.Produto;

public interface ProdutoRepository {

	List<Produto> findProducts(Produto produto);
	
}
