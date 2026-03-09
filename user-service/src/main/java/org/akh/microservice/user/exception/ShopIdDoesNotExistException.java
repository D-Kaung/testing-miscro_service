package org.akh.microservice.user.exception;

public class ShopIdDoesNotExistException extends RuntimeException {

    public ShopIdDoesNotExistException(String message) {
        super(message);
    }
}
