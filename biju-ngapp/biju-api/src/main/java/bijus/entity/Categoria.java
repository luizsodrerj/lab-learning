package bijus.entity;

public enum Categoria {

	BIJUTERIA("Bijuteria"),
	SEMIJOIA("Semij\u00F3ia"),
	JOIA("J\u00F3ia");
	
	
	private String descricao;
	
	
	Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
//	public static void main(String[] args) {
//		List<Categoria>list = Arrays.asList(Categoria.values());
//	}
	
}







