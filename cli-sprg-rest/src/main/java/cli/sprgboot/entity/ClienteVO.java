package cli.sprgboot.entity;

import java.io.Serializable;

public class ClienteVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nomeRazaoSocial;
	private String dataCadastro;
	private String nomeMatriz;
	private String idMatriz;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNomeMatriz() {
		return nomeMatriz;
	}

	public void setNomeMatriz(String nomeMatriz) {
		this.nomeMatriz = nomeMatriz;
	}

	public String getIdMatriz() {
		return idMatriz;
	}

	public void setIdMatriz(String idMatriz) {
		this.idMatriz = idMatriz;
	}
	
	
}
