import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produit } from '../Model/product';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  constructor(private http: HttpClient) { }

  public get(id: String): Observable<Produit> {
    return this.http.get<Produit>(`http://localhost:8086/api/products/${id}`);
  }

  public getAll() : Observable<Produit[][]>{
    return this.http.get<Produit[][]>(`http://localhost:8086/api/productsParLotde20`);
  }

  public isEmpty(): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8086/api/emptydb`);
  }

  public db(): any {
    return this.http.delete(`http://localhost:8086/api/database`);
  }


}
