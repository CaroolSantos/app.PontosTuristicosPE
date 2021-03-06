import { FavoritosProvider } from './../../providers/favoritos/favoritos';
import { Component, ViewChild, ElementRef, ChangeDetectorRef } from '@angular/core';
import { IonicPage, NavController, NavParams, LoadingController } from 'ionic-angular';
import { ListaResultadosProvider } from '../../providers/lista-resultados/lista-resultados';
import { Geolocation } from '@ionic-native/geolocation';


declare var google;

@IonicPage()
@Component({
  selector: 'page-detalhes-local',
  templateUrl: 'detalhes-local.html',
})
export class DetalhesLocalPage {
  @ViewChild('map') mapElement: ElementRef;
  map: any;
  service: any;

  local: any;
  loader: any;
  favorito = false;
  favoritos = [];
  avaliacao: number;


  constructor(public navCtrl: NavController, public navParams: NavParams, public detalhesServico: ListaResultadosProvider,
    public loadingCtrl: LoadingController, public favoritosServico: FavoritosProvider, private geolocation: Geolocation, public cDetect: ChangeDetectorRef) {

    this.local = this.navParams.get("local");
    console.log("INFO - local carregado " + this.local);
  }

  ionViewDidLoad() {
    this.loadMap();
    console.log('ionViewDidLoad DetalhesLocalPage');
    this.favoritosServico.listaFavoritos()
      .then((data) => {
        console.log('data - ' + data);
        if (data) {
          this.favoritos = JSON.parse(data);
          console.log("favoritos - " + JSON.stringify(this.favoritos));

          this.favoritos = this.favoritos.filter(f => f.id == this.local.id);
          console.log("favoritos FILTER - " + JSON.stringify(this.favoritos));
          if (this.favoritos.length > 0) {
            this.favorito = true;
            console.log("favoritos true");
          }
        }
      })

  }

  addfavorito() {
    this.favoritosServico.addFavorito(this.local);
    this.favorito = true;
  }

  rmfavorito() {
    this.favoritosServico.rmFavorito(this.local);
    this.favorito = false;
  }

  loadMap() {
    console.log("entrou no load map");


    let latLng = new google.maps.LatLng(this.local.latitude, this.local.longitude);

    let mapOptions = {
      center: latLng,
      zoom: 17,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      zoomControl: false,
      streetViewControl: false,
      fullscreenControl: false,
      mapTypeControl: false
    }

    var request = {
      location: latLng,
      query: this.local.name
    
    };


    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions)
    this.service = new google.maps.places.PlacesService(this.map);
    var self = this;
    this.service.textSearch(request, (results, status) =>{
      this.cDetect.detectChanges();
      if (status == google.maps.places.PlacesServiceStatus.OK) {
        console.log('log - ' + JSON.stringify(results));
        if(results.length > 0){
          self.avaliacao = results[0].rating;
          
          console.log('avaliação - ' + self.avaliacao);
        }
      }
    });
    this.addMarker();

  }

  // callback(results, status) {
  //   if (status == google.maps.places.PlacesServiceStatus.OK) {
  //     console.log('log - ' + JSON.stringify(results));
  //     if(results.length > 0){
  //       self.avaliacao = results[0].rating;
  //     }
  //   }
  // }

  addMarker() {

    let marker = new google.maps.Marker({
      map: this.map,
      animation: google.maps.Animation.DROP,
      position: this.map.getCenter()
    });

    let content = "<h4>Information!</h4>";

    this.addInfoWindow(marker, content);

  }

  addInfoWindow(marker, content) {

    let infoWindow = new google.maps.InfoWindow({
      content: content
    });

    google.maps.event.addListener(marker, 'click', () => {
      infoWindow.open(this.map, marker);
    });

  }
}
