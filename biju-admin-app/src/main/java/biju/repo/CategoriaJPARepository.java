package biju.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pdv.domain.Categoria;

public interface CategoriaJPARepository extends JpaRepository<Categoria, Integer> {
		
    List<Categoria> findAllByOrderByNomeAsc();

}
