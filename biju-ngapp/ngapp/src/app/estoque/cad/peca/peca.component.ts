import { Component, OnInit } from '@angular/core';

import { PecaValue } from 'src/vo/pecaValue';

@Component({
  selector: 'app-peca',
  templateUrl: './peca.component.html',
  styles: ['.div-form-group { margin-bottom: 5px; }']
})
export class PecaComponent implements OnInit {

  peca: PecaValue = new PecaValue();

  file: File;

  
  constructor() { }

  ngOnInit() {
  }

  onFileSelect(event) {
    let fileEvt = event.originalEvent; 
  }

}
