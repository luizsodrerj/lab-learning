import { Component } from '@angular/core';
import { HttpClient, HttpRequest } from '@angular/common/http';

@Component({
  selector: 'app-up',
  templateUrl: './up.component.html'
})
export class UpComponent {

  private file: File;

  
  constructor(private http: HttpClient) {
  }

  onChangeFile(event) {
    const selectedFiles = <FileList>event.srcElement.files;
    this.file = selectedFiles[0];

    console.log(this.file.name);
  }

  upload() {
    const formData = new FormData();
    const url      = 'http://localhost:8080/bijus-api/api/upload';

    formData.append('file', this.file, this.file.name);

    const request = new HttpRequest('POST', url, formData);

    this.http.request(request)
        .subscribe(response => 
          console.log('Upload concluido com sucesso!')
        );
  }  

}
