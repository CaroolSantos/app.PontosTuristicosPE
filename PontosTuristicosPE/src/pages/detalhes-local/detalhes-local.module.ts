import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { DetalhesLocalPage } from './detalhes-local';

@NgModule({
  declarations: [
    DetalhesLocalPage,
  ],
  imports: [
    IonicPageModule.forChild(DetalhesLocalPage),
  ],
})
export class DetalhesLocalPageModule {}
