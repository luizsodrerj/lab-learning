import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgxMaskModule } from 'ngx-mask';

import { AppRoutingModule } from './app-routing.module';
import { CarouselModule } from 'primeng/carousel';
import { AppComponent } from './app.component';

import { IndexContentComponent } from 'src/index/index.content.component';
import { SemiJoiasComponent } from 'src/index/semijoias.component';
import { JoiasComponent } from 'src/index/joias.component';
import { BijusComponent } from 'src/index/bijus.component';
import { ContatoFormComponent } from 'src/contato/contato-form.component';
import { ContatoComponent } from 'src/contato/contato.component';
import { UpComponent } from 'src/index/up.component';
import { NavbarComponent } from 'src/estoque/navbar.component';
import { TipoPecaComponent } from 'src/estoque/cad/tipo-peca.comp';

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
    TipoPecaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgxMaskModule.forRoot(),
    HttpClientModule,
    CarouselModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
}


