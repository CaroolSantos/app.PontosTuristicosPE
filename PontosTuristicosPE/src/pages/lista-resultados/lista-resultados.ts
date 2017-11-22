import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { ListaResultadosProvider } from '../../providers/lista-resultados/lista-resultados';

@IonicPage()
@Component({
  selector: 'page-lista-resultados',
  templateUrl: 'lista-resultados.html',
})
export class ListaResultadosPage {
  id_categoria: any;
  loader: any;
  resultados: any;
  id_cidade:any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public listagemServico: ListaResultadosProvider, public loadingCtrl: LoadingController) {
  }

  ionViewDidLoad() {
    this.presentLoading();
    this.id_categoria = this.navParams.get("id");
    this.id_cidade = this.navParams.get("id_cidadeSelecionada");

    if(this.id_cidade==null){
      console.log('id_cidade null');
      
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
    }else{
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

}
