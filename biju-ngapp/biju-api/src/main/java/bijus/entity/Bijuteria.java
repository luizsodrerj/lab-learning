package bijus.entity;

public class Bijuteria extends Peca {

	private static final long serialVersionUID = -556661433620640454L;
	
	private static final String CATEGORIA_BIJU = "Bijuteria";

	
	@Override
	public String getCategoria() {
		return CATEGORIA_BIJU;
	}
	
}
