import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';


let apiUrlIndicacao = "http://localhost:8100/api/places/";

@Injectable()
export class IndicacaoLocalProvider {

  constructor(public http: Http) {
    console.log('Hello IndicacaoLocalProvider Provider');
  }

  indicarLocal(local,idCidade,idCategoria){
    let ind = {
      "address": local.address,
      "name": local.name,
      "language": "0",
      "latitude": "0",
      "longitude": "0",
      "altitude": "0",
      "category": {
          "id": parseInt(idCategoria)
      },
      "city": {
          "id": parseInt(idCidade)
      },
      "description":local.description
    };

    
    console.log("info - " + JSON.stringify(ind));

    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'Bearer'});
    let options = new RequestOptions({ headers: headers });

    return this.http.post(apiUrlIndicacao, ind, options)
    .map(res => res.json());
  
  }

}
