import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { PecaValue } from 'src/vo/pecaValue';
import { Categoria } from 'src/vo/categoria';

@Component({
  selector: 'app-peca',
  templateUrl: './peca.component.html',
  styles: ['.div-form-group { margin-bottom: 5px; }']
})
export class PecaComponent implements OnInit {

  categoria: Categoria = new Categoria();
  app: AppComponent = new AppComponent();
  peca: PecaValue = new PecaValue();

  categorias: String[] = [
                this.categoria.BIJUTERIA,
                this.categoria.SEMIJOIA,
                this.categoria.JOIA
              ];
  file: File;

  
  constructor() { }

  ngOnInit() {
  }

  onFileSelect(event) {
    const fileEvt       = event.originalEvent; 
    const selectedFiles = <FileList>fileEvt.srcElement.files;
    this.file           = selectedFiles[0];

    console.log(this.file.name);
  }


}



