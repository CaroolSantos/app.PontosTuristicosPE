import { IndicacaoLocalProvider } from './../../providers/indicacao-local/indicacao-local';
import { Component, ViewChild, ElementRef } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular/components/alert/alert-controller';
import { HomePage } from '../home/home';
import { Geolocation } from '@ionic-native/geolocation';
import { NativeGeocoder, NativeGeocoderReverseResult, NativeGeocoderForwardResult } from '@ionic-native/native-geocoder';

declare var google;

@IonicPage()
@Component({
  selector: 'page-indicacao',
  templateUrl: 'indicacao.html',
})
export class IndicacaoPage {
  @ViewChild('map') mapElement: ElementRef;
  map: any;

  local: any={};
  idCidade: number;
  idCategoria:number;
 
  constructor(public navCtrl: NavController, public navParams: NavParams, public indicacaoServico: IndicacaoLocalProvider, 
    public alertCtrl: AlertController, private nativeGeocoder: NativeGeocoder, public geolocation: Geolocation) {
  }

  ionViewDidLoad() {
    this.loadMap();
    console.log('ionViewDidLoad IndicacaoPage');
  }

  loadMap() {
    
        this.geolocation.getCurrentPosition().then((position) => {
    
          let latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    
          let mapOptions = {
            center: latLng,
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            zoomControl: false,
            streetViewControl: false,
            fullscreenControl: false,
            mapTypeControl: false
          }
    
          this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

    
          let marker = new google.maps.Marker({
            map: this.map,
            draggable: true,
            animation: google.maps.Animation.DROP,
            position: this.map.getCenter()
          });
    
          google.maps.event.addListener(marker, 'dragend', (event) => {
            console.log("marcador arrastado" + marker.getPosition().lat() + "- " + marker.position);
            this.reverseGeocode(marker.getPosition().lat(), marker.getPosition().lng());
          });
    
          this.reverseGeocode(position.coords.latitude, position.coords.longitude);
    
        }, (err) => {
          console.log(err);
        });
    
      }
    
      reverseGeocode(latitude, longitude) {
        this.nativeGeocoder.reverseGeocode(latitude, longitude)
          .then((result: NativeGeocoderReverseResult) => {
            console.log('The address is ' + JSON.stringify(result));
            this.local.address = result.locality + ", " + result.subLocality + " - " + result.countryName;
    
            this.local.latitude = latitude;
            this.local.longitude = longitude;
    
          })
          .catch((error: any) => console.log("erro no geocoder reverso" + JSON.stringify(error)));
    
      }

  indicarLocal(){
    
    console.log('INFO local - ' + JSON.stringify(this.local));
    this.indicacaoServico.indicarLocal(this.local,this.idCidade,this.idCategoria)
    .subscribe(
      data => {

        console.log("INFO - sucesso ao indicar local " + JSON.stringify(data));
        this.exibirAlert("Local indicado com sucesso!", "Nossos consultores irão avaliar as informações e futuramente o local estará disponível no app.");
        this.navCtrl.setRoot(HomePage);

      },
      err => {
        console.log('ERROR - problema ao indicar local ' + err);
        this.exibirAlert("Erro!", "Ocorreu um erro ao tentar indicar o local, tente novamente.");
      })

  }

  exibirAlert(titulo, subtitulo) {
    let alert = this.alertCtrl.create({
      title: titulo,
      subTitle: subtitulo,
      buttons: ['OK']
    });
    alert.present();

  }

}
