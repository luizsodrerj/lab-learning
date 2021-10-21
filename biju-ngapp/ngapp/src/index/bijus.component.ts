import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';

import { PecasViewHelper } from 'src/index/pecas-view-helper';
import { AppComponent } from 'src/app/app.component';
import { Peca } from 'src/vo/peca';

@Component({
  selector: 'app-bijus',
  templateUrl: './bijus.component.html'
})
export class BijusComponent implements OnInit {

  app: AppComponent = new AppComponent();
  pecasList: Peca[] = [];


  constructor(
      private _sanitizer: DomSanitizer,
      private http: HttpClient 
   ) {}

  ngOnInit() {
    const base = this.app.API;
    let url    = base + '/bijus/get-all-bijus';

    this.http.get<any>(url)
        .subscribe(dados => {
          this.populate(dados);
        });    
  }

  populate(dados: any) {
      let viewHelper: PecasViewHelper = new PecasViewHelper(this._sanitizer);

      this.pecasList = viewHelper.populatePecasData(dados);
  }


}
