package biju.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pdv.domain.StatusProduto;

public interface StatusJPARepository extends JpaRepository<StatusProduto, Integer> {

}
