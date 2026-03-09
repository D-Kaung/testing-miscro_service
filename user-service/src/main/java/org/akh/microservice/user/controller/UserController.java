package org.akh.microservice.user.controller;

import lombok.NonNull;
import org.akh.microservice.user.DTO.ShopWithProductsDTO;
import org.akh.microservice.user.DTO.UserCreateDTO;
import org.akh.microservice.user.DTO.OrderCreateDTO;
import org.akh.microservice.user.exception.ProductIdDoesNotExistException;
import org.akh.microservice.user.feign.Products;
import org.akh.microservice.user.service.UserService;
import org.akh.microservice.user.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    public UserController(UserService userService, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;

    }

    @PostMapping("/create")
    ResponseEntity<@NonNull UserCreateDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
     return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }

    @GetMapping("/products/{id}")
    Products getProductsById(@PathVariable Long id) {
        return userServiceImpl.getProductsById(id);
    }

    @GetMapping("/products")
    List<Products> getProductsList() {
        return userServiceImpl.getProductsList();
    }

    @GetMapping("/shop-products/{id}")
    ShopWithProductsDTO getAllProductsListFromShopWithShopId(@PathVariable Long id) {
        return userServiceImpl.getShopWithProductsByShopId(id);
    }

    @PostMapping("/create-order/{id}")
    ResponseEntity<?> createUserOrderRequest(@RequestBody OrderCreateDTO userOrderCreateDTO,
                                            @PathVariable Long id) {
        try {
            var userOrder = userServiceImpl.userOrderCreate(userOrderCreateDTO,id);
           return ResponseEntity.ok(userOrder);
        }catch (ProductIdDoesNotExistException e){
            return ResponseEntity.ok("DOES NOT EXIST PRODUCTS WITH THIS ID");
        }

    }

}
