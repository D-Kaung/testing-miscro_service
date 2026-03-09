package org.akh.microservice.orderserviceextend.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductsInterface{

    @GetMapping("/products/get-products-by-id/{id}")
    Products getProductsById(@PathVariable Long id);

    @GetMapping("/products/get-productsList")
    List<Products> getAllProducts();
}
