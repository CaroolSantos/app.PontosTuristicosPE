import { UsuarioProvider } from './../../providers/usuario/usuario';
import { LoginPage } from './../login/login';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IonicPage, NavController, NavParams, AlertController, LoadingController } from 'ionic-angular';
import { HomePage } from '../home/home';

@IonicPage()
@Component({
  selector: 'page-cadastro-usuario',
  templateUrl: 'cadastro-usuario.html',
})
export class CadastroUsuarioPage {
  registerForm: FormGroup;
  loader;

  constructor(public navCtrl: NavController, public navParams: NavParams, public formBuilder: FormBuilder, 
    public usuarioProvider: UsuarioProvider, public loadingCtrl: LoadingController, public alertCtrl: AlertController) {
    this.registerForm = formBuilder.group({
      Nome: ['', Validators.compose([Validators.required])],
      Email: ['', Validators.compose([Validators.required, Validators.email])],
      Password: ['', Validators.compose([Validators.required])]
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastroUsuarioPage');
  }

  login() {
    this.navCtrl.setRoot(LoginPage);
  }

  registrar(){
    let nav = this.navCtrl;
    let env = this;
    let aleCtrl = this.alertCtrl;
      if (this.registerForm.valid) {
        
        this.showLoading('Aguarde...');

        this.usuarioProvider.registro(this.registerForm.controls.Nome.value, this.registerForm.controls.Email.value, this.registerForm.controls.Password.value)
          .subscribe(
          data => {
            //todo salvar nome do usuario
            nav.setRoot(HomePage);
            this.loader.dismiss();

          },
          err => {

            let alert = this.alertCtrl.create({
              title: 'Erro',
              subTitle: 'Aconteceu algo de errado ao realizar o registro, por favor tente novamente.',
              buttons: ['OK']
            });
            alert.present();
            this.loader.dismiss();

          }
          )
      } else {

        let alert = this.alertCtrl.create({
          title: 'Inválido',
          subTitle: 'Por favor preencha todos os campos obrigatórios.',
          buttons: ['OK']
        });
        alert.present();

      }
    } 

  showLoading(msg) {
    this.loader = this.loadingCtrl.create({
      content: msg
    });
    this.loader.present();
  }

}
