import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexContentComponent } from 'src/index/index.content.component';
import { HomeAdminComponent } from 'src/app/estoque/home.admin.component';
import { TipoPecaComponent } from 'src/app/estoque/cad/tipo-peca.comp';

const routes: Routes = [
  {path: 'homeAdmin', component: HomeAdminComponent},
  {path: 'tipoPeca', component: TipoPecaComponent},
  {path: '', component: IndexContentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
