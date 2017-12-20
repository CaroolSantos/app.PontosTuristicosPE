import { FavoritosProvider } from './../../providers/favoritos/favoritos';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { DetalhesLocalPage } from '../detalhes-local/detalhes-local';


@IonicPage()
@Component({
  selector: 'page-favoritos',
  templateUrl: 'favoritos.html',
})
export class FavoritosPage {
  resultados= [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public favoritosServico: FavoritosProvider) {
  }

  ionViewDidEnter() {
    console.log('ionViewDidLoad FavoritosPage');
    this.favoritosServico.listaFavoritos()
    .then((data) =>{
      if(data){
        this.resultados = JSON.parse(data);
      }
    })
  }

  abrirDetalhes(local) {
    console.log('detalhes local - ' + local)
    this.navCtrl.push(DetalhesLocalPage, { local: local });
  }

}
