import { DomSanitizer } from '@angular/platform-browser';

export class Peca {

    constructor(private sanitizer: DomSanitizer) {}

	public id: number;
	public base64Image: String;
	public qtdEstoque: number;
	public descricao: String     
	public categoria: String
	public tipo: String
	public status: String
	public formatedPreco: String
    public imagePath: any;
    

    public copy(peca: any) {
        this.categoria = peca.categoria;
        this.base64Image = peca.base64Image;
        this.formatedPreco = peca.formatedPreco;
        this.qtdEstoque = peca.qtdEstoque;
        this.descricao  = peca.descricao;
        this.categoria = peca.categoria;
        this.status = peca.status;
        this.tipo = peca.tipo;
        this.id = peca.id;
    }

    public setImage() {
        this.imagePath = this.sanitizer.bypassSecurityTrustResourceUrl(
                            'data:image/jpg;base64,' + 
                            this.base64Image
                         );
    }

}