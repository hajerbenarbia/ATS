package tn.esprit.spring.services;

import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.repo.ProductRepositoryy;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
public class ProductService {

    @Autowired
    ProductRepositoryy pr;


    public Iterable<Produit> list() {
        return pr.findAll();
    }


    @Autowired
    EntityManager entityManager;

    public List<Produit> findpage( int offset, int limit) {
        TypedQuery<Produit> query = entityManager.createQuery("SELECT e FROM Produit e  ORDER BY e.id", Produit.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }


    public List<Produit> getAllProducts(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Produit> pagedResult = pr.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Produit>();
        }
    }



    public Produit save(Produit user) {
        return pr.save(user);
    }

    public void save(List<Produit> users) {
        pr.saveAll(users);
    }

    public Produit FindProduitById (long id) {
        return pr.findById(id).orElse(new Produit());
    }

    public List<Produit> getAllProduit() {
        return (List<Produit>) pr.findAll();
    }

}
