package bijus.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pecas")
public class PecasVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<PecaVO>pecas = new ArrayList<PecaVO>();


	public PecasVO(List<PecaVO> pecas) {
		this.pecas = pecas;
	}

	public PecasVO() {
		super();
	}

	public List<PecaVO> getPecas() {
		return pecas;
	}

	public void setPecas(List<PecaVO> pecas) {
		this.pecas = pecas;
	}
	
	

}
