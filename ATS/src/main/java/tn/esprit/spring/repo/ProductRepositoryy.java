package tn.esprit.spring.repo;




import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Produit;

import java.util.List;


@Repository
public interface ProductRepositoryy  extends PagingAndSortingRepository<Produit,Long > {
    @Query("SELECT COUNT(c) FROM Produit c")
    long getNombreProduits();
}
