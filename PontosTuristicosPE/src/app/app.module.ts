import { CadastroUsuarioPage } from './../pages/cadastro-usuario/cadastro-usuario';
import { LoginPage } from './../pages/login/login';
import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { HttpModule } from '@angular/http';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { ListPage } from '../pages/list/list';
import { ListaResultadosPage } from './../pages/lista-resultados/lista-resultados';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { ListaResultadosProvider } from '../providers/lista-resultados/lista-resultados';

import { MatchHeightDirective } from '../directives/match-height/match-height';
import { UsuarioProvider } from '../providers/usuario/usuario';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    ListPage,
    ListaResultadosPage,
    MatchHeightDirective,
    LoginPage,
    CadastroUsuarioPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    ListPage,
    ListaResultadosPage,
    LoginPage,
    CadastroUsuarioPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    ListaResultadosProvider,
    UsuarioProvider
  ]
})
export class AppModule {}
