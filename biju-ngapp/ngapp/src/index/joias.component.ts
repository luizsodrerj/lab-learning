import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';

import { AppComponent } from 'src/app/app.component';
import { Peca } from 'src/vo/peca';

@Component({
  selector: 'app-joias',
  templateUrl: './joias.component.html'
})
export class JoiasComponent implements OnInit {

  app: AppComponent = new AppComponent();
  list: Peca[] = [];


  constructor(
      private _sanitizer: DomSanitizer,
      private http: HttpClient 
   ) {}

  ngOnInit() {
    const base = this.app.API;
    let url    = base + '/bijus/get-all-joias';

    this.http.get<any>(url)
        .subscribe(dados => {
          this.populate(dados);
        });    
  }

  populate(dados: any) {
    let pecas: any[] = dados.pecas;

    pecas.forEach(joia => {
      let peca = new Peca(this._sanitizer);

      peca.categoria = joia.categoria;
      peca.base64Image = joia.base64Image;
      peca.formatedPreco = joia.formatedPreco;
      peca.qtdEstoque = joia.qtdEstoque;
      peca.descricao  = joia.descricao;
      peca.categoria = joia.categoria;
      peca.tipo = joia.tipo;
      peca.status = joia.status;
      peca.id = joia.id;

      peca.setImage();

      this.list.push(peca);
    });
  }


}
