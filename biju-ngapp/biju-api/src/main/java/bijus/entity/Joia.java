package bijus.entity;

public class Joia extends Peca {

	private static final long serialVersionUID = 1L;
	
	private static final String CATEGORIA_JOIA = "J\u00F3ia";

	
	@Override
	public String getCategoria() {
		return CATEGORIA_JOIA;
	}
	
}
