package org.akh.microservice.productsserviceextend.repostiory;

import org.akh.microservice.productsserviceextend.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Products,Long> {

 List<Products> findAll();
}
