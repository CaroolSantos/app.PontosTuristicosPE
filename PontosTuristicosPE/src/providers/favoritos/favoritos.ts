import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Storage } from '@ionic/storage';


@Injectable()
export class FavoritosProvider {
  favoritos = [];

  constructor(public http: Http, public storage: Storage) {
    console.log('Hello FavoritosProvider Provider');
  }

  addFavorito(local) {
    console.log('local - ' + local);
    this.storage.get("favoritos")
      .then((data) => {
        console.log('data - ' + data);
        if (data) {
          this.favoritos = JSON.parse(data);
        }
        this.favoritos.push(local);
        console.log("favoritos - " + JSON.stringify(this.favoritos));

        this.storage.set("favoritos", JSON.stringify(this.favoritos));
      })
  }

  rmFavorito(local) {
    console.log('local - ' + local);
    this.storage.get("favoritos")
      .then((data) => {
        console.log('data - ' + data);
        if (data) {
          this.favoritos = JSON.parse(data);
          console.log("favoritos - " + JSON.stringify(this.favoritos));

          this.favoritos = this.favoritos.filter(f => f.id != local.id);
          this.storage.set("favoritos", JSON.stringify(this.favoritos));
        }
      })
  }

  listaFavoritos() {
    return this.storage.get("favoritos");
  }

}
