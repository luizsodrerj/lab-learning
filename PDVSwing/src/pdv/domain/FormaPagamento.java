package pdv.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FORMA_PAGAMENTO database table.
 * 
 */
@Entity
@Table(name="FORMA_PAGAMENTO")
@NamedQuery(name="FormaPagamento.findAll", query="SELECT f FROM FormaPagamento f order by forma")
public class FormaPagamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String NAO_INFORMADA = "N\u00E3o informada.";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String forma;

	
	
	public FormaPagamento() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getForma() {
		return this.forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
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
		FormaPagamento other = (FormaPagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}