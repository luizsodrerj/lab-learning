import { DomSanitizer } from '@angular/platform-browser';

import { Peca } from 'src/vo/peca';

export class PecasViewHelper {

    constructor(private sanitizer: DomSanitizer) {}
  
    public populatePecasData(dados: any): Peca[] {
        let pecasList: Peca[] = [];

        let pecas: any[] = dados.pecas;

        pecas.forEach(joia => {
          let peca = new Peca(this.sanitizer);
    
          peca.copy(joia);
          peca.setImage();
    
          pecasList.push(peca);
        });
        
        return pecasList;
    }

}