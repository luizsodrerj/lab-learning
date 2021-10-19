import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';

import { AppComponent } from 'src/app/app.component';
import { Peca } from 'src/vo/peca';

@Component({
  selector: 'app-joias',
  templateUrl: './joias.component.html',
  styles: [`
  .carousel-demo .ui-carousel .ui-carousel-content .ui-carousel-item .car-details > .p-grid {
      border: 1px solid #b3c2ca;
      border-radius: 3px;
      margin: 0.3em;
      text-align: center;
      padding: 2em 0 2.25em 0;
  }
  .car-title {
      font-weight: 700;
      font-size: 20px;
      margin-top: 24px;
  }
  .car-subtitle {
      margin: 0.25em 0 2em 0;
      font-weight: bold;
  }
  .carousel-demo .ui-carousel .ui-carousel-content .ui-carousel-item .car-data button {
      margin-left: 0.5em;
  }
  .carousel-demo .ui-carousel .ui-carousel-content .ui-carousel-item .car-data button:first-child {
      margin-left: 0;
  }
  .carousel-demo .ui-carousel.custom-carousel .ui-carousel-dot-icon {
      width: 16px !important;
      height: 16px !important;
      border-radius: 50%;
  }
  .carousel-demo .ui-carousel.ui-carousel-horizontal .ui-carousel-content .ui-carousel-item.ui-carousel-item-start .car-details > .p-grid {
      margin-left: 0.6em;
  }
  .carousel-demo .ui-carousel.ui-carousel-horizontal .ui-carousel-content .ui-carousel-item.ui-carousel-item-end .car-details > .p-grid {
      margin-right: 0.6em;
  }
`],  
})
export class JoiasComponent implements OnInit {

  app: AppComponent = new AppComponent();
  pecasList: Peca[] = [];


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

      peca.copy(joia);
      peca.setImage();

      this.pecasList.push(peca);
    });
  }


}
