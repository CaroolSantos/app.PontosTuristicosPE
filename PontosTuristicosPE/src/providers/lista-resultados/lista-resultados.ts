import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';


let apiListaUrl = "http://localhost:8100/api/places/category";

@Injectable()
export class ListaResultadosProvider {

  constructor(public http: Http) {
    console.log('Hello ListaResultadosProvider Provider');
  }

  abrirListaLocais(id) {
    var url = apiListaUrl + "/" + id;
    return this.http.get(url)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

}
