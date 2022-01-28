import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Produit } from '../Model/product';
import { ProduitService } from '../services/produit.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private service : ProduitService) { }
  product : Produit;

  ngOnInit(): void {
  }

  public getProduct(s : string): void {
    this.service.get(s).subscribe(
      (response: Produit) => {
        this.product = response;
        console.log(this.product);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  

}
