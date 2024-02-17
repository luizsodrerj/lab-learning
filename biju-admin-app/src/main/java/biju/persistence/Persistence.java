package biju.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;

import framework.persistence.jpa.PersistenceServiceUtil;

public class Persistence extends PersistenceServiceUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ORDER_DESC = "desc";
	public static final String ORDER_ASC  = "asc";
	
	public <T> List<T> findAll(
						  Class<T> type, 
						  String orderBy, 
						  String orderType,
						  int maxResults 
					   ) {
		connect();
		
		EntityManager manager = getEntityManager();
		StringBuilder jpaQL	  =	new StringBuilder("select o from ")
								.append(type.getSimpleName())
								.append(" o ");
		
		orderBy = orderBy != null  && orderBy.trim().length() > 0 ? 
				  " order by o." + orderBy : 
				  "";
		String ordType = orderBy != null  && 
						 orderBy.trim().length() > 0 && 
						 StringUtils.isNotBlank(orderType) ?
						 " " + orderType :
						 "";
						 
		jpaQL.append(orderBy).append(ordType);
		
		Query query = manager.createQuery(jpaQL.toString());
		
		if (maxResults > 0) {
			query.setMaxResults(maxResults);
		}
		
		return query.getResultList();
	}
	
	
}





