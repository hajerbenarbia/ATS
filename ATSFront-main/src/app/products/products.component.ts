import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Produit } from '../Model/product';
import { ProduitService } from '../services/produit.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor(private service : ProduitService ){
  
  }

public detailsHidden = true ;
public shopHidden = false;

subscription: Subscription;

  empty : boolean;
  detailsproduct : Produit;
  products : Produit[][];
  productsList : Produit[];

  ngOnInit(): void {
    this.getdbifempty();
    this.getProducts();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  
  public getProducts(): void {
    this.service.getAll().subscribe(
      (response: Produit[][]) => {
        this.products = response;
        this.productsList=response[0];
        console.log(this.products);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getdbifempty() : void {
    this.subscription = this.service.isEmpty().subscribe((response : boolean) => {
      if (response) {
        console.log("db charging");
        this.db();
      }this.empty = response;
    });
   
  }

  public db(): void {
    this.service.db().subscribe(
      (response: void) => {
        console.log("done");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public isEmpty(): void {
    
  }

  public click1() : void  {
    this.shopHidden = false;
    this.detailsHidden = true;
    this.productsList=this.products[0];
    console.log(this.productsList);
  }

  public click2() : void  {
    this.shopHidden = false;
    this.detailsHidden = true;
    this.productsList=this.products[1];
    console.log(this.productsList);
  }

  public click3() : void  {
    this.shopHidden = false;
    this.detailsHidden = true;
    this.productsList=this.products[2];
    console.log(this.productsList);
  }
  public click4() : void  {
    this.shopHidden = false;
    this.detailsHidden = true;
    this.productsList=this.products[3];
    console.log(this.productsList);
  }
  public click5() : void  {
    this.shopHidden = false;
    this.detailsHidden = true;
    this.productsList=this.products[4];
    console.log(this.productsList);
  }

  public Details(product) : void {
    this.detailsHidden=false;
    this.shopHidden = true;
    this.getProduct(product.idproduct);
  }

  public search(key: string): void {
    const results: Produit[] = [];
    for (const product of this.productsList) {
      if (product.category.toLowerCase().indexOf(key.toLowerCase()) !== -1 )
      {
        results.push(product);
      }
    }
    this.productsList = results;
  }

  public getProduct(s : string): void {
    this.service.get(s).subscribe(
      (response: Produit) => {
        this.detailsproduct = response;
        console.log(this.detailsproduct);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
