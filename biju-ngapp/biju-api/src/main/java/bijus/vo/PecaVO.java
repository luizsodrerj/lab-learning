package bijus.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;

import bijus.entity.Peca;
import framework.util.FormatNumberUtil;

@XmlRootElement
public class PecaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String base64Image;
	
	private Integer qtdEstoque;
	private String descricao;
	private String categoria;
	private String tipo;
	private String status;
	private String formatedPreco;
	
	
	
	public void copy(Peca peca) {
		try {
			BeanUtils.copyProperties(this, peca);
			
			if (peca.getPreco() != null) {
				Double preco  = peca.getPreco();
				formatedPreco = FormatNumberUtil.format(preco, FormatNumberUtil.FORMATO_QTD_DEC2);
			}
			if (peca.getImagem() != null) {
				byte[]img   = peca.getImagem();
				base64Image = Base64.encodeBase64String(img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFormatedPreco() {
		return formatedPreco;
	}
	public void setFormatedPreco(String formatedPreco) {
		this.formatedPreco = formatedPreco;
	}


	
	
}
