package org.akh.microservice.shopservice.handler;

import org.akh.microservice.shopservice.exception.ShopIdNotHaveException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShopIdNotHaveException.class)
    public ResponseEntity<?> handleShopIdNotFoundException(RuntimeException e) {
        return ResponseEntity.badRequest().body("ShopId doesn't exist");
    }
}
