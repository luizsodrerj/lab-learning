package biju.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pdv.domain.Produto;

public interface ProdutoJPARepository extends JpaRepository<Produto, Integer>, ProdutoRepository {

	List<Produto> findTop12ByOrderByIdDesc();
	
}
