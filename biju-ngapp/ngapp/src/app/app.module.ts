import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgxMaskModule } from 'ngx-mask';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FileUploadModule } from 'primeng/fileupload';
import { CarouselModule } from 'primeng/carousel';
import { DropdownModule } from 'primeng/dropdown';

import { IndexContentComponent } from 'src/index/index.content.component';
import { SemiJoiasComponent } from 'src/index/semijoias.component';
import { JoiasComponent } from 'src/index/joias.component';
import { BijusComponent } from 'src/index/bijus.component';
import { ContatoFormComponent } from 'src/contato/contato-form.component';
import { ContatoComponent } from 'src/contato/contato.component';
import { UpComponent } from 'src/index/up.component';
import { NavbarComponent } from 'src/app/estoque/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { HomeAdminComponent } from 'src/app/estoque/home.admin.component';
import { TipoPecaComponent } from 'src/app/estoque/cad/tipo-peca/tipo-peca.comp';
import { PecaComponent } from 'src/app/estoque/cad/peca/peca.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexContentComponent,
    SemiJoiasComponent,
    JoiasComponent,
    BijusComponent,
    ContatoFormComponent,
    ContatoComponent,
    UpComponent,
    NavbarComponent,
    TipoPecaComponent,
    FooterComponent,
    HomeAdminComponent,
    PecaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgxMaskModule.forRoot(),
    HttpClientModule,
    FileUploadModule,
    CarouselModule,
    DropdownModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}


