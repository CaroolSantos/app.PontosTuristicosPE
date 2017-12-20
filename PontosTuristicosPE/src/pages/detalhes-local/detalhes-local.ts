import { FavoritosProvider } from './../../providers/favoritos/favoritos';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { ListaResultadosProvider } from '../../providers/lista-resultados/lista-resultados';



@IonicPage()
@Component({
  selector: 'page-detalhes-local',
  templateUrl: 'detalhes-local.html',
})
export class DetalhesLocalPage {
  local: any;
  loader: any;
  favorito= false;
  favoritos = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public detalhesServico: ListaResultadosProvider, 
    public loadingCtrl: LoadingController,  public favoritosServico: FavoritosProvider) {

    this.local = this.navParams.get("local");
    console.log("INFO - local carregado " + this.local);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DetalhesLocalPage');
    this.favoritosServico.listaFavoritos()
    .then((data) => {
      console.log('data - ' + data);
      if (data) {
        this.favoritos = JSON.parse(data);
        console.log("favoritos - " + JSON.stringify(this.favoritos));

        this.favoritos = this.favoritos.filter(f => f.id == this.local.id);
        console.log("favoritos FILTER - " + JSON.stringify(this.favoritos));
        if(this.favoritos.length > 0){
          this.favorito = true;
          console.log("favoritos true");
        }
      }
    })

  }

  addfavorito(){
    this.favoritosServico.addFavorito(this.local);
    this.favorito = true;
  }

  rmfavorito(){
    this.favoritosServico.rmFavorito(this.local);
    this.favorito = false;
  }
}
