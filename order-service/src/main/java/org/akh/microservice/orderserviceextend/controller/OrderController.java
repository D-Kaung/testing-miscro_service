package org.akh.microservice.orderserviceextend.controller;

import org.akh.microservice.orderserviceextend.DTO.OrderCreateDTO;
import org.akh.microservice.orderserviceextend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDTO orderCreateDTO,
                                                      @PathVariable Long id) {
        try{
            var order = orderService.createOrder(orderCreateDTO,id);
            return ResponseEntity.ok().body(order);
        }catch(Exception e){
            return ResponseEntity.ok("DOES NOT EXIST PRODUCTS WITH THIS ID!!!");
        }

    }


}
