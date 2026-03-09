package org.akh.microservice.shopservice.service;

import org.akh.microservice.shopservice.dto.ShopCreateDTO;
import org.akh.microservice.shopservice.dto.ShopWithProductsDTO;
import org.akh.microservice.shopservice.entity.Shop;

import java.util.List;

public interface ShopService {

    ShopCreateDTO createShop(ShopCreateDTO shopCreateDTO);

    List<Shop> getAllShops();

    ShopWithProductsDTO  getShopWithProductsByShopId(Long id);
}
