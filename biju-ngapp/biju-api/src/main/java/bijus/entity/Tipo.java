package bijus.entity;

public enum Tipo {

	COLAR("Colar"),
	PULSEIRA("Pulseira"),
	COLAR_PINGENTE("Colar com Pingente"),
	ANEL("Anel"),
	BROCHE("Broche"),
	RELOGIO("Rel\u00F3gio"),
	OUTROS_JOIAS("Outros Tipos de J\u00F3ias"),
	OUTROS_BIJU("Outros Tipos de Bijuterias");
	
	
	private String descricao;
	
	Tipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
}
