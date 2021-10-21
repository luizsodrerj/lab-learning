package bijus.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import bijus.entity.TipoPeca;

@XmlRootElement(name = "tiposPeca")
public class TiposPecaVO {

	private List<TipoPeca>tipos = new ArrayList<TipoPeca>();

	
	public TiposPecaVO(List<TipoPeca> tipos) {
		super();
		this.tipos = tipos;
	}

	public List<TipoPeca> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoPeca> tipos) {
		this.tipos = tipos;
	}
	
	
	
	
}
