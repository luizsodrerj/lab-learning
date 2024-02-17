package biju.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import pdv.domain.Produto;

public class ProdutoJPARepositoryImpl implements ProdutoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	@Transactional(readOnly = true)	
	public List<Produto> findProducts(Produto produto) {
		Session delegate = entityManager.unwrap(Session.class);
		Criteria crit = delegate.createCriteria(Produto.class, "prod");
		
		addCriteria(produto, crit);
		
		crit.addOrder(Order.asc("prod.nome"));
		crit.setMaxResults(450);

		return crit.list();
	}

	private void addCriteria(Produto produto, Criteria crit) {
		if (StringUtils.isNotBlank(produto.getNome())) {
			crit.add(Restrictions.ilike(
				"prod.nome", 
				"%"+produto.getNome()+"%"
			));
		}
		if (StringUtils.isNotBlank(produto.getDescricao())) {
			crit.add(Restrictions.ilike(
				"prod.descricao", 
				"%"+produto.getDescricao()+"%"
			));
		}
		if (produto.getStatusProduto() != null && produto.getStatusProduto().getId() != null) {
			if (produto.isDisponivel()) {
				Criterion crtStat = Restrictions.eq("prod.statusProduto.id", Integer.valueOf(1));
				Criterion crtQtd  = Restrictions.gt("prod.qtdEstoque", Integer.valueOf(0));
				Criterion or 	  = Restrictions.or(crtQtd, crtStat);
				crit.add(or);
			} else {
				crit.add(Restrictions.eq(
					"prod.statusProduto.id", 
					produto.getStatusProduto().getId()
				));
			}
		}
		if (produto.getCategoria() != null && produto.getCategoria().getId() != null) {
			crit.add(Restrictions.eq(
				"prod.categoria.id", 
				produto.getCategoria().getId()
			));
		}
	}
	
	
}










