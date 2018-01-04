import { DetalhesLocalPage } from './../pages/detalhes-local/detalhes-local';
import { CadastroUsuarioPage } from './../pages/cadastro-usuario/cadastro-usuario';
import { LoginPage } from './../pages/login/login';
import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { HttpModule } from '@angular/http';
import { IonicStorageModule } from '@ionic/storage';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { ListaResultadosPage } from './../pages/lista-resultados/lista-resultados';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { ListaResultadosProvider } from '../providers/lista-resultados/lista-resultados';

import { MatchHeightDirective } from '../directives/match-height/match-height';
import { UsuarioProvider } from '../providers/usuario/usuario';
import { FavoritosPage } from '../pages/favoritos/favoritos';
import { FavoritosProvider } from '../providers/favoritos/favoritos';
import { IndicacaoPage } from '../pages/indicacao/indicacao';
import { IndicacaoLocalProvider } from '../providers/indicacao-local/indicacao-local';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    ListaResultadosPage,
    MatchHeightDirective,
    LoginPage,
    CadastroUsuarioPage,
    DetalhesLocalPage,
    FavoritosPage,
    IndicacaoPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule,
    IonicStorageModule.forRoot()
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    ListaResultadosPage,
    LoginPage,
    CadastroUsuarioPage,
    DetalhesLocalPage,
    FavoritosPage,
    IndicacaoPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    ListaResultadosProvider,
    UsuarioProvider,
    FavoritosProvider,
    IndicacaoLocalProvider
  ]
})
export class AppModule {}
