package tn.esprit.spring;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.repo.ProductRepositoryy;
import tn.esprit.spring.services.ProductService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class AtsApplication {


	public static void main(String[] args) {
		SpringApplication.run(AtsApplication.class, args);
	}







}
