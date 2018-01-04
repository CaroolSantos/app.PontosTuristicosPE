import { IndicacaoLocalProvider } from './../../providers/indicacao-local/indicacao-local';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular/components/alert/alert-controller';
import { HomePage } from '../home/home';

@IonicPage()
@Component({
  selector: 'page-indicacao',
  templateUrl: 'indicacao.html',
})
export class IndicacaoPage {
  local: any={};
  idCidade: number;
  idCategoria:number;
 
  constructor(public navCtrl: NavController, public navParams: NavParams, public indicacaoServico: IndicacaoLocalProvider, public alertCtrl: AlertController) {
  }

  ngOnInit() {
    console.log('ionViewDidLoad IndicacaoPage');
  }

  indicarLocal(){
    
    console.log('INFO local - ' + JSON.stringify(this.local));
    this.indicacaoServico.indicarLocal(this.local,this.idCidade,this.idCategoria)
    .subscribe(
      data => {

        console.log("INFO - sucesso ao indicar local " + JSON.stringify(data));
        this.exibirAlert("Local indicado com sucesso!", "Nossos consultores irão avaliar as informações e futuramente o local estará disponível no app.");


      },
      err => {
        console.log('ERROR - problema ao indicar local ' + err);
        this.exibirAlert("Erro!", "Ocorreu um erro ao tentar indicar o local, tente novamente.");
      })

  }

  exibirAlert(titulo, subtitulo) {
    let alert = this.alertCtrl.create({
      title: titulo,
      subTitle: subtitulo,
      buttons: ['OK']
    });
    alert.present();

  }

}
