package org.akh.microservice.shopservice.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.akh.microservice.shopservice.dto.ShopCreateDTO;
import org.akh.microservice.shopservice.dto.ShopListDTO;
import org.akh.microservice.shopservice.dto.ShopWithProductsDTO;
import org.akh.microservice.shopservice.entity.Shop;
import org.akh.microservice.shopservice.feign.Products;
import org.akh.microservice.shopservice.mapper.ShopMapper;
import org.akh.microservice.shopservice.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    public ShopController(ShopService shopService, ShopMapper shopMapper) {
        this.shopService = shopService;
        this.shopMapper = shopMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<ShopCreateDTO> createShop(@RequestBody ShopCreateDTO shopCreateDTO) {
        return ResponseEntity.ok(shopService.createShop(shopCreateDTO));
    }

    @GetMapping("/get-shops")
    List<Shop> getAllShopsList() {
        return shopService.getAllShops();
    }

    @GetMapping("/get-products-with-shop/{id}")
    ResponseEntity<?> getShopWithProductsList(@PathVariable Long id) {
        try{
            var shopWithProducts = (shopService.getShopWithProductsByShopId(id));
            return ResponseEntity.ok(shopWithProducts);
        }catch (RuntimeException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
