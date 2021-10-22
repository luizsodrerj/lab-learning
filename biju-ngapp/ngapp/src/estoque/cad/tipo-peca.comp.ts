import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-tipo-peca',
  templateUrl: './tipo-peca.comp.html'
})
export class TipoPecaComponent implements OnInit {

  tipoPecaPlaceholder: string = 'Informe o Tipo de Pe\u00E7a';

  app: AppComponent = new AppComponent();
  tiposPeca: any[] = [];

  
  constructor(private http: HttpClient) { }

  ngOnInit() {
    const base = this.app.API;
    const url  = base + '/tipo-peca/get-all';

    this.http.get<any>(url)
        .subscribe(dados => {
          this.populate(dados);
        });    
  }

  populate(dados: any) {
    let list: any[] = dados.tipos;

    list.forEach(tipo => {
      this.tiposPeca.push(tipo);
    });
  }

}




