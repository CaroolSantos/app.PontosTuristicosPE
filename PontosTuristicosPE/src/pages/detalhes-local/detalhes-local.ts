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

  constructor(public navCtrl: NavController, public navParams: NavParams, public detalhesServico: ListaResultadosProvider, public loadingCtrl: LoadingController) {

    this.local = this.navParams.get("local");
    console.log("INFO - local carregado " + this.local);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DetalhesLocalPage');

  }
}
