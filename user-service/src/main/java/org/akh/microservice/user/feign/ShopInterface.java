package org.akh.microservice.user.feign;

import org.akh.microservice.user.DTO.ShopWithProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shop-service")
public interface ShopInterface {

    @GetMapping("/shop/get-products-with-shop/{id}")
    ShopWithProductsDTO getShopWithProductsList(@PathVariable Long id);

}
