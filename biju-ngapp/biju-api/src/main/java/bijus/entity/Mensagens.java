package bijus.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Mensagens")
public class Mensagens implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Mensagem> mensagens;

	public Mensagens(List<Mensagem> mensagens) {
		super();
		this.mensagens = mensagens;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
	
	
}
