package org.akh.microservice.shopservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.akh.microservice.shopservice.dto.ShopCreateDTO;
import org.akh.microservice.shopservice.dto.ShopWithProductsDTO;
import org.akh.microservice.shopservice.entity.Shop;
import org.akh.microservice.shopservice.exception.ShopIdNotHaveException;
import org.akh.microservice.shopservice.feign.Products;
import org.akh.microservice.shopservice.feign.ProductsInterface;
import org.akh.microservice.shopservice.mapper.ShopMapper;
import org.akh.microservice.shopservice.repository.ShopRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepo  shopRepo;
    private final ShopMapper shopMapper;
    private final ProductsInterface productsInterface;
    private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    public ShopServiceImpl(ShopRepo shopRepo, ShopMapper shopMapper,ProductsInterface productsInterface) {
        this.shopRepo = shopRepo;
        this.shopMapper = shopMapper;
        this.productsInterface = productsInterface;
    }

    @Override
    public ShopCreateDTO createShop(ShopCreateDTO shopCreateDTO) {
        Shop shop = shopMapper.toEntity(shopCreateDTO);
        shop.setShopName(shopCreateDTO.getShopName());
        shop.setShopAddress(shopCreateDTO.getShopAddress());
        shopRepo.save(shop);
        logger.info("SHOP CREATED SUCCESSFULLY...");
        return shopMapper.toDTO(shop);
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepo.findAll();
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallBackShopWithProductsByShopId")
    public ShopWithProductsDTO getShopWithProductsByShopId(Long id)throws ShopIdNotHaveException {
        Shop shop = shopRepo.findById(id).orElseThrow(() ->
                new ShopIdNotHaveException("THERE IS NOT HAVE THIS SHOP ID"));
        List<Products> productsList = productsInterface.getProductsList();
        ShopWithProductsDTO shopWithProductsDTO = new ShopWithProductsDTO();
        shopWithProductsDTO.setId(shop.getId());
        shopWithProductsDTO.setShopName(shop.getShopName());
        shopWithProductsDTO.setShopAddress(shop.getShopAddress());
        shopWithProductsDTO.setProductsList(productsList);
        logger.info("TRYING TO FETCH ALL PRODUCTS LIST WITH SHOP ID IS SUCCESSFULLY...");
        return shopWithProductsDTO;
    }

    public ShopWithProductsDTO fallBackShopWithProductsByShopId(Long id, Throwable e)throws Exception {
        logger.error("PRODUCTS SERVICE IS DOWN!!");
        Shop shop =  shopRepo.findById(id).orElseThrow(() ->
                new RuntimeException("THERE IS NOT HAVE THIS SHOP ID"));
        return mapToDto(shop, Collections.emptyList());
    }

    private ShopWithProductsDTO mapToDto(Shop shop, List<Products> productsList) {
        ShopWithProductsDTO shopWithProductsDTO = new ShopWithProductsDTO();
        shopWithProductsDTO.setId(shop.getId());
        shopWithProductsDTO.setShopName(shop.getShopName());
        shopWithProductsDTO.setShopAddress(shop.getShopAddress());
        shopWithProductsDTO.setProductsList(productsList);
        return shopWithProductsDTO;
    }

}
