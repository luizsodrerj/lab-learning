package pdv.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TOTAL_VENDAS_POR_DATA database table.
 * 
 */

@Entity
@Table(name="TOTAL_VENDAS_POR_DATA")
public class TotalVendasPorData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_VENDA")
	private Date dataVenda;

	@Column(name="VALOR_TOTAL")
	private Double valorTotal;

	
	
	public TotalVendasPorData() {
	}

	public Date getDataVenda() {
		return this.dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}