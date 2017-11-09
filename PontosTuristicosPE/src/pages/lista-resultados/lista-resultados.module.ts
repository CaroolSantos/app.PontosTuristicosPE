import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ListaResultadosPage } from './lista-resultados';

@NgModule({
  declarations: [
    ListaResultadosPage,
  ],
  imports: [
    IonicPageModule.forChild(ListaResultadosPage),
  ],
})
export class ListaResultadosPageModule {}
