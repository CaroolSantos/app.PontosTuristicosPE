import { CadastroUsuarioPage } from './../cadastro-usuario/cadastro-usuario';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
  loginForm: FormGroup;
  loader;

  constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder, public loadingCtrl: LoadingController) {
    this.loginForm = formBuilder.group({
      Email: ['', Validators.compose([Validators.required, Validators.email])],
      Password: ['', Validators.compose([Validators.required])]
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

  
  showLoading(msg) {
    this.loader = this.loadingCtrl.create({
      content: msg
    });
    this.loader.present();
  }

  novaConta(){
    this.navCtrl.push(CadastroUsuarioPage);
  }
}
