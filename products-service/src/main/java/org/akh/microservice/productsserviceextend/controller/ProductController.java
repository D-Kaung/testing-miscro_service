package org.akh.microservice.productsserviceextend.controller;

import org.akh.microservice.productsserviceextend.DTO.ProductsCreateDTO;
import org.akh.microservice.productsserviceextend.entity.Products;
import org.akh.microservice.productsserviceextend.mapper.ProductsMapper;
import org.akh.microservice.productsserviceextend.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductsMapper productsMapper;
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService,
                                   ProductsMapper productsMapper) {
        this.productsService = productsService;
        this.productsMapper = productsMapper;
    }

    @GetMapping("/get-products-by-id/{id}")
    public Optional<Products> getProductsById(@PathVariable Long id){
        return productsService.getProductsById(id);
    }

    @GetMapping("/get-productsList")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productsService.getProductsList());
    }

    @PostMapping("/create")
    public ResponseEntity<ProductsCreateDTO> productsCreateDTO(@RequestBody ProductsCreateDTO productsCreateDTO) {
        return ResponseEntity.ok().body(productsService.createProduct(productsCreateDTO));
    }
}
