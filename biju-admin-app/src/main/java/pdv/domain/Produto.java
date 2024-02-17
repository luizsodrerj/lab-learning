package pdv.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

import biju.util.IOUtil;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	private String descricao;
	private String nome;
	private Double preco;

	@Column(name = "QTD_ESTOQUE")
	private Integer qtdEstoque;

	@Lob
	@Column(name = "IMAGEM")
    private byte[] imagem;

	@ManyToOne
    @JoinColumn(name = "ID_STATUS")
    private StatusProduto statusProduto;	
	
	@ManyToOne
    @JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;
	

	
	public Produto(
		     String descricao, 
		     String nome, 
		     Double preco, 
		     Integer qtdEstoque, 
		     byte[] imagem,
		     StatusProduto statusProduto, 
		     Categoria categoria
		   ) {
		super();
		this.statusProduto = statusProduto;
		this.categoria = categoria;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.imagem = imagem;
		this.nome = nome;
		this.preco = preco;
	}

	public Produto() {
	}

	public boolean isDisponivel() {
		return	statusProduto != null && statusProduto.getId() != null ?
				statusProduto.getId().equals(StatusProduto.DISPONIVEL) :
				qtdEstoque != null && qtdEstoque.intValue() > 0;	
	}
	
	@Transient
	public StreamedContent getImageStream() {
		StreamedContent s = IOUtil.imageBytesToStreamedContent(
					imagem, 
					IOUtil.IMAGE_PNG
				);
		return s; 
    }

	public StatusProduto getStatusProduto() {
		return statusProduto;
	}

	public void setStatusProduto(StatusProduto statusProduto) {
		this.statusProduto = statusProduto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
