package org.akh.microservice.user.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.akh.microservice.user.DTO.ShopWithProductsDTO;
import org.akh.microservice.user.DTO.UserCreateDTO;
import org.akh.microservice.user.DTO.OrderCreateDTO;
import org.akh.microservice.user.entity.User;
import org.akh.microservice.user.exception.ProductIdDoesNotExistException;
import org.akh.microservice.user.exception.ShopIdDoesNotExistException;
import org.akh.microservice.user.feign.OrderInterface;
import org.akh.microservice.user.feign.Products;
import org.akh.microservice.user.feign.ProductsInterface;
import org.akh.microservice.user.feign.ShopInterface;
import org.akh.microservice.user.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ProductsInterface productsInterface;
    private final ShopInterface  shopInterface;
    private final OrderInterface orderInterface;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepo userRepo, ProductsInterface productsInterface, ShopInterface shopInterface,
                         OrderInterface orderInterface) {
        this.userRepo = userRepo;
        this.productsInterface = productsInterface;
        this.shopInterface = shopInterface;
        this.orderInterface = orderInterface;
    }

    @Override
    public UserCreateDTO createUser(UserCreateDTO userCreateDTO) {
        User createUser = new User();
        createUser.setUsername(userCreateDTO.getUserName());
        createUser.setPassword(userCreateDTO.getPassword());
        createUser.setEmail(userCreateDTO.getEmail());
        userRepo.save(createUser);
        logger.info("User created successfully..");
        return userCreateDTO;
    }

    @Retry(name = "order-service")
    @CircuitBreaker(name = "order-service", fallbackMethod = "fallbackUserOrderCreate")
    public OrderCreateDTO userOrderCreate(OrderCreateDTO userOrderCreateDTO, Long id)throws RuntimeException {
        if (id == null) {
            throw new ProductIdDoesNotExistException("THERE IS NOT HAVE PRODUCT ID");
        }
        return orderInterface.createOrder(userOrderCreateDTO, id);
    }

    public OrderCreateDTO fallbackUserOrderCreate(OrderCreateDTO userOrderCreateDTO, Long id,Throwable ex) {
        userOrderCreateDTO.setQty(userOrderCreateDTO.getQty());
        userOrderCreateDTO.setProductName("ORDER-SERVICE IS DOWN!!!");
        userOrderCreateDTO.setPrice(null);
        return userOrderCreateDTO;
    }

    public List<Products> getProductsList() {
        return productsInterface.getProductsList();
    }

    public Products getProductsById(Long id) {
        return productsInterface.getProductsById(id);
    }

    @Retry(name = "shop-service")
    @CircuitBreaker(name = "shop-service", fallbackMethod = "fallBackShop")
    public ShopWithProductsDTO getShopWithProductsByShopId(Long id)throws NoSuchElementException {
        ShopWithProductsDTO shopWithProductsDTO = shopInterface.getShopWithProductsList(id);
        if (shopWithProductsDTO == null) {
            throw new ShopIdDoesNotExistException("THERE IS NOT HAVE SHOP ID");
        }
        return shopWithProductsDTO;
    }

    ShopWithProductsDTO fallBackShop(Long id,Throwable throwable)throws Exception {
      logger.error("SHOP SERVICE IS DOWN!!!");
      ShopWithProductsDTO fallBackShop = new ShopWithProductsDTO();
      fallBackShop.setId(id);
      fallBackShop.setShopName("SHOP SERVICE IS DOWN!!!!");
      fallBackShop.setShopAddress("");
      fallBackShop.setProductsList(Collections.emptyList());
      return fallBackShop;
    }
}
