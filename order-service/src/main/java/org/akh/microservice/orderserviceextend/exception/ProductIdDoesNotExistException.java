package org.akh.microservice.orderserviceextend.exception;

public class ProductIdDoesNotExistException extends RuntimeException {
    public ProductIdDoesNotExistException(String message) {
        super(message);
    }
}
