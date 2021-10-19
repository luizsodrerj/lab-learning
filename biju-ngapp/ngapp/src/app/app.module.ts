import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgxMaskModule } from 'ngx-mask';

import { AppRoutingModule } from './app-routing.module';
import { CarouselModule } from 'primeng/carousel';
import { AppComponent } from './app.component';

import { IndexContentComponent } from 'src/index/index.content.component';
import { JoiasComponent } from 'src/index/joias.component';
import { UpComponent } from 'src/index/up.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexContentComponent,
    JoiasComponent,
    UpComponent
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


