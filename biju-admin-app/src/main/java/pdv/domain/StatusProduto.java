package pdv.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STATUS_PRODUTO database table.
 * 
 */
@Entity
@Table(name="STATUS_PRODUTO")
@NamedQuery(name="StatusProduto.findAll", query="SELECT s FROM StatusProduto s")
public class StatusProduto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final Integer DISPONIVEL  = 1;
	public static final Integer ESGOTADO 	= 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String status;

	
	public StatusProduto(Integer id, String status) {
		super();
		this.status = status;
		this.id = id;
	}

	public StatusProduto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusProduto other = (StatusProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}