package tn.esprit.spring.controllers;


import java.io.FileReader;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.repo.ProductRepositoryy;
import tn.esprit.spring.services.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "*")
public class ProduitController {

    @Autowired
    ProductService ps ;

    @Autowired
    ProductRepositoryy pr;


    @GetMapping("products/{id}")
    public Produit getProduitByUsername(@PathVariable("id") String id){
        return ps.FindProduitById(Long.valueOf(id));
    }
    @PostMapping("save")
    public Produit save(@RequestBody Produit p){
        return ps.save(p);
    }


    @GetMapping("/emptydb")
    public boolean emptydb(){
        boolean empty = true;
        if ( pr.getNombreProduits() > 0) { empty = false ;}
        return empty;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Produit>> getAllA(){
        List<Produit> produits = ps.getAllProduit();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }
    @DeleteMapping("database")
    public void savedata(){
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:\\Users\\LENOVO\\Documents\\workspace-sts-3.8.4.RELEASE\\ATS\\src\\main\\java\\tn\\esprit\\spring\\json\\test.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("products");

            for (int i=0;i<(companyList.size());i++){
                Produit d = new Produit();

                JSONObject rec = (JSONObject) companyList.get(i);
                String productName = rec.get("productName").toString();
                long price = (long) rec.get("price");
                String imageUrl = rec.get("imageUrl").toString();
                String description = rec.get("description").toString();
                String category = rec.get("category").toString();

                d.setProductName(productName);
                d.setPrice(price);
                d.setImageUrl(imageUrl);
                d.setDescription(description);
                d.setCategory(category);

                ps.save(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    /*@GetMapping("/products")
    public ResponseEntity<List<Produit>> getProductsPaginated(){
        List<Produit> produits = ps.getAllProduit();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }*/

    @GetMapping("productsParLotde20")
    public ResponseEntity<List<ArrayList<Produit>>> getAllProducts(
           // @RequestParam(defaultValue = "0") Integer pageNo,
            //@RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "idproduct") String sortBy)
    {
        List<ArrayList<Produit>> listOfLists = new ArrayList<ArrayList<Produit>>();

        List<Produit> list =   ps.getAllProducts(0, 20, sortBy);
        List<Produit> list2 =  ps.getAllProducts(1, 20, sortBy);
        List<Produit> list3 =  ps.getAllProducts(2, 20, sortBy);
        List<Produit> list4 =  ps.getAllProducts(3, 20, sortBy);
        List<Produit> list5 =  ps.getAllProducts(4, 20, sortBy);
        ArrayList<Produit> l1 = new ArrayList<Produit>(list);
        ArrayList<Produit> l2 = new ArrayList<Produit>(list2);
        ArrayList<Produit> l3 = new ArrayList<Produit>(list3);
        ArrayList<Produit> l4 = new ArrayList<Produit>(list4);
        ArrayList<Produit> l5 = new ArrayList<Produit>(list5);
        listOfLists.add(l1);
        listOfLists.add(l2);
        listOfLists.add(l3);
        listOfLists.add(l4);
        listOfLists.add(l5);


        return new ResponseEntity<List<ArrayList<Produit>>>(listOfLists, new HttpHeaders(), HttpStatus.OK);
    }






}
