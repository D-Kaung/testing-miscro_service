package org.akh.microservice.user.handler;

import org.akh.microservice.user.exception.ProductIdDoesNotExistException;
import org.akh.microservice.user.exception.ShopIdDoesNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductIdDoesNotExistException.class)
    public ResponseEntity<?>  productIdDoesNotExistException(RuntimeException exception) {
        return ResponseEntity.badRequest().body("Product Id doesn't exist");
    }

    @ExceptionHandler(ShopIdDoesNotExistException.class)
    public ResponseEntity<?> shopIdDoesNotExistException(RuntimeException exception) {
        return ResponseEntity.badRequest().body("Shop Id doesn't exist");
    }
}
