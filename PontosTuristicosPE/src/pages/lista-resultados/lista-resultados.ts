import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController, AlertController } from 'ionic-angular';
import { ListaResultadosProvider } from '../../providers/lista-resultados/lista-resultados';
import { DetalhesLocalPage } from '../detalhes-local/detalhes-local';

@IonicPage()
@Component({
  selector: 'page-lista-resultados',
  templateUrl: 'lista-resultados.html',
})
export class ListaResultadosPage {
  id_categoria: any;
  loader: any;
  resultados: any;
  id_cidade: any;
  stringBusca: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public listagemServico: ListaResultadosProvider,
    public loadingCtrl: LoadingController, private alertCtrl: AlertController) {
  }

  ionViewDidLoad() {
    this.presentLoading();
    this.id_categoria = this.navParams.get("id");
    this.id_cidade = this.navParams.get("id_cidadeSelecionada");
    this.stringBusca = this.navParams.get("stringBusca");

    if (this.id_cidade == null) {
      console.log('id_cidade null');
      if (this.id_categoria == null) {
        this.listagemServico.buscarPorNome(this.stringBusca)
          .subscribe(
          data => {
            this.resultados = data;

            console.log('resultado ' + JSON.stringify(this.resultados));
            this.loader.dismiss();
          },
          err => {
            console.log('[ERROR] ' + err);
            this.loader.dismiss();
          },
          () => console.log("lista de locais carregada")
          );
      } else {
        this.listagemServico.abrirListaLocais(this.id_categoria)
          .subscribe(
          data => {
            this.resultados = data;

            console.log('resultado ' + JSON.stringify(this.resultados));
            this.loader.dismiss();
          },
          err => {
            console.log('[ERROR] ' + err);
            this.loader.dismiss();
          },
          () => console.log("lista de locais carregada")
          );
      }

    } else {
      console.log('id_cidade NOT null');

      this.listagemServico.abrirListaLocaisCidade(this.id_categoria, this.id_cidade)
        .subscribe(
        data => {
          this.resultados = data;

          console.log('resultado ' + JSON.stringify(this.resultados));
          this.loader.dismiss();
        },
        err => {
          console.log('[ERROR] ' + err);
          this.loader.dismiss();
        },
        () => console.log("lista de locais carregada")
        );
    }

  }

  presentLoading() {

    this.loader = this.loadingCtrl.create({
      content: "Carregando lista de locais..."
    });

    this.loader.present();
  }

  abrirDetalhes(local) {
    console.log('detalhes local - ' + local)
    this.navCtrl.push(DetalhesLocalPage, { local: local });
  }

  pesquisarLocal(stringBusca){
    console.log('testando pesquisar ');
    if(stringBusca == null){
      this.presentAlert();

    } else{
      this.navCtrl.push(ListaResultadosPage,{stringBusca: stringBusca})
    }
    
    
  }

  presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Erro ao buscar!',
      subTitle: 'Digite um local para pesquisar..',
      buttons: ['Ok']
    });
    alert.present();
  }

}
