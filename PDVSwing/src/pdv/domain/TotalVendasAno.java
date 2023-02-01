package pdv.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TOTAL_VENDAS_ANO database table.
 * 
 */
@Entity
@Table(name="TOTAL_VENDAS_ANO")
public class TotalVendasAno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer ano;

	@Column(name="VALOR_TOTAL")
	private Double valorTotal;

	
	public TotalVendasAno() {
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}