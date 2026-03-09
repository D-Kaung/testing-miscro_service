package org.akh.microservice.user.exception;

public class ProductIdDoesNotExistException extends RuntimeException  {
    public ProductIdDoesNotExistException(String message) {
        super(message);
    }
}
