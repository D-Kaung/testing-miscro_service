package org.akh.microservice.productsserviceextend.service;

import org.akh.microservice.productsserviceextend.DTO.ProductsCreateDTO;
import org.akh.microservice.productsserviceextend.entity.Products;

import java.util.List;
import java.util.Optional;

public interface ProductsService {

    ProductsCreateDTO  createProduct(ProductsCreateDTO productsCreateDTO);

   List<Products>  getProductsList();

   Optional<Products> getProductsById(Long id);
}
