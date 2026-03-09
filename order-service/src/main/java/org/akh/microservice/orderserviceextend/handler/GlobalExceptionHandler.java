package org.akh.microservice.orderserviceextend.handler;

import org.akh.microservice.orderserviceextend.exception.ProductIdDoesNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductIdDoesNotExistException.class)
    public ResponseEntity<?> handleProductIdNotFoundException(RuntimeException ex){
        return ResponseEntity.badRequest().body("Product Id doesn't exist");
    }
}
