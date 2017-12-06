import { UsuarioProvider } from './../../providers/usuario/usuario';
import { CadastroUsuarioPage } from './../cadastro-usuario/cadastro-usuario';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController, AlertController } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HomePage } from '../home/home';

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
  loginForm: FormGroup;
  loader;

  constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder,
    public loadingCtrl: LoadingController, public usuarioProvider: UsuarioProvider, public alertCtrl: AlertController) {
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

  novaConta() {
    this.navCtrl.push(CadastroUsuarioPage);
  }

  entrar() {
    if (this.loginForm.valid) {
      this.showLoading('Entrando...');
      this.usuarioProvider.login(this.loginForm.controls.Email.value, this.loginForm.controls.Password.value)
        .subscribe(data => {
          this.loader.dismiss();
          this.navCtrl.setRoot(HomePage);
        },
        err => {
          this.loader.dismiss();
          let alert = this.alertCtrl.create({
            title: 'Erro',
            subTitle: 'Aconteceu algo de errado, tente novamente mais tarde.',
            buttons: ['OK']
          });
          alert.present();
          console.log('ERROR - login ' + JSON.stringify(err));
        }
        )
    } else {
      let alert = this.alertCtrl.create({
        title: 'Campos inválidos',
        subTitle: 'Preencha o usuário e a senha.',
        buttons: ['OK']
      });
      alert.present();
    }
  }
}
