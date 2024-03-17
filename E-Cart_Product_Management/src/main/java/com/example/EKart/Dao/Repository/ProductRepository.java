package com.example.EKart.Dao.Repository;

import com.example.EKart.Dao.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.ProductName) LIKE LOWER(:query) OR " +
            "LOWER(p.ProductType) LIKE LOWER(:query)")
    List<Product> searchProducts(@Param("query") String query);




    @Query(value = "Select p from Product p Where p.IsActive=TRUE", nativeQuery = true)
    ResponseEntity<List<Product>> getallProduct();

}




