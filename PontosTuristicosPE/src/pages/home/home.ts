import { ListaResultadosPage } from './../lista-resultados/lista-resultados';
import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController) {

  }

  abrirLista(id_tipocategoria){
    this.navCtrl.push(ListaResultadosPage, {id: id_tipocategoria})
  }

}
