import { Reviews } from "./review"

export class Produit{
  idproduct: number
	productName: string
	description: string
	price: number
	category: string
	imageUrl:string
  reviews : Reviews[]
}
