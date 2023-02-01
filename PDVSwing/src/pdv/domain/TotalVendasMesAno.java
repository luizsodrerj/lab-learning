package pdv.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TOTAL_VENDAS_MES_ANO database table.
 * 
 */
@Entity
@Table(name="TOTAL_VENDAS_MES_ANO")
@NamedQuery(name="TotalVendasMesAno.findAll", query="SELECT t FROM TotalVendasMesAno t")
public class TotalVendasMesAno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ANO_MES")
	private String anoMes;

	@Column(name="VALOR_TOTAL")
	private Double valorTotal;

	
	public TotalVendasMesAno() {
	}

	public String getAnoMes() {
		return this.anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public Double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}