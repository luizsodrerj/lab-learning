import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import {DropdownModule} from 'primeng/dropdown';
import { PecaValue } from 'src/vo/pecaValue';
import { Categoria } from 'src/vo/categoria';

@Component({
  selector: 'app-peca',
  templateUrl: './peca.component.html',
  styles: ['.div-form-group { margin-bottom: 5px; }']
})
export class PecaComponent implements OnInit {

  app: AppComponent = new AppComponent();
  peca: PecaValue = new PecaValue();

  categoria: Categoria = new Categoria();
  selectedCategoria: String;
  categorias: Categoria[];

  file: File;

  
  constructor() { 
    this.categorias = this.categoria.getCategorias();
  }

  ngOnInit() {
  }

  onFileSelect(event) {
    const fileEvt       = event.originalEvent; 
    const selectedFiles = <FileList>fileEvt.srcElement.files;
    this.file           = selectedFiles[0];

    console.log(this.file.name);
  }


}



