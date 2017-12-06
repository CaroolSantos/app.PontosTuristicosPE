import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

let registroUrl = "http://localhost:8100/api/users";
let loginUrl = "http://localhost:8100/api/users/login";

@Injectable()
export class UsuarioProvider {

  constructor(public http: Http) {
    console.log('Hello UsuarioProvider Provider');
  }

  registro(nome, email, senha) {
  let user = {
    name: nome,
    email: email,
    password: senha
  }
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(registroUrl, JSON.stringify(user), options);
  }

  login(usuario, senha){
    let user = {
      email: usuario,
      password: senha
    }
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    console.log('login ok -> ' + JSON.stringify(user))
    return this.http.post(loginUrl, JSON.stringify(user), options);
  }

}
