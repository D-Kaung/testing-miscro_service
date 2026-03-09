package org.akh.microservice.shopservice.feign;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service", fallback = ProductFallback.class)
public interface ProductsInterface {

    @GetMapping("/products/get-productsList")
    @Retry(name = "products-service")
    List<Products> getProductsList();

    @GetMapping("/products/get-products-by-id/{id}")
    Products getProductsById(@PathVariable Long id);
}
