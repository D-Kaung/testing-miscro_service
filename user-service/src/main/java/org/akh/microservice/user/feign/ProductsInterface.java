package org.akh.microservice.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductsInterface {

    @GetMapping("/products/get-productsList")
    List<Products> getProductsList();

    @GetMapping("/products/get-products-by-id/{id}")
    Products getProductsById(@PathVariable Long id);

}

