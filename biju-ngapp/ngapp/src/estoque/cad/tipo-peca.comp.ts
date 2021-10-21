import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tipo-peca',
  templateUrl: './tipo-peca.comp.html'
})
export class TipoPecaComponent implements OnInit {

  tipoPecaPlaceholder: string = 'Informe o Tipo de Pe\u00E7a';

  tiposPeca: any[] = [];

  
  constructor() { }

  ngOnInit() {
  }

}
