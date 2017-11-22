import { ListaResultadosProvider } from './../../providers/lista-resultados/lista-resultados';
import { ListaResultadosPage } from './../lista-resultados/lista-resultados';
import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NavController } from 'ionic-angular';
import 'rxjs/add/operator/debounceTime';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  searchTerm: string = '';
  searchControl: FormControl;
  cidades: any;
  cidadeSelecionada: boolean=false;
  exibirLista: boolean=true;
  id_cidadeSelecionada: any;

  constructor(public navCtrl: NavController, public listagemServico: ListaResultadosProvider) {
    this.searchControl = new FormControl();

  }

  ionViewDidLoad() {

    this.setFilteredItems();
    this.searchControl.valueChanges.debounceTime(400).subscribe(search => {
      if(this.exibirLista==false){
        this.cidadeSelecionada=false;
        this.id_cidadeSelecionada=null;
        console.log('cidade null' + this.id_cidadeSelecionada);
      }
      if(this.cidadeSelecionada){
        this.exibirLista=false;
      }
      this.setFilteredItems();
      

    });

  }

  setFilteredItems() {

    this.cidades = this.listagemServico.filterItems(this.searchTerm);

  }

  atualizaCidade(cidade){
    this.searchTerm=cidade.nome;
    this.cidadeSelecionada=true;
    this.id_cidadeSelecionada=cidade.id_cidade;
    console.log('cidade selecionada' + this.id_cidadeSelecionada);

    if(this.exibirLista==false){
      this.exibirLista=true;
    }
  }

  abrirLista(id_tipocategoria) {
    this.navCtrl.push(ListaResultadosPage, { id: id_tipocategoria, id_cidadeSelecionada: this.id_cidadeSelecionada })
  }

}
