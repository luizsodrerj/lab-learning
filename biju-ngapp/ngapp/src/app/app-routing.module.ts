import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexContentComponent } from 'src/index/index.content.component';
import { HomeAdminComponent } from 'src/app/estoque/home.admin.component';
import { TipoPecaComponent } from 'src/app/estoque/cad/tipo-peca/tipo-peca.comp';
import { PecaComponent } from './estoque/cad/peca/peca.component';

const routes: Routes = [
  {path: 'homeAdmin', component: HomeAdminComponent},
  {path: 'tipoPeca', component: TipoPecaComponent},
  {path: 'cadPeca', component: PecaComponent},
  {path: '', component: IndexContentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
