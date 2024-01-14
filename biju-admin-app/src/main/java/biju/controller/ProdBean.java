package biju.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "prodBean")
@SessionScoped
public class ProdBean {

	public String redirectToForm() {
		return "/cadastro/produto/FormProduto.xhtml";
	}

}
