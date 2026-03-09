package org.akh.microservice.shopservice.exception;

public class ShopIdNotHaveException extends RuntimeException {

    public ShopIdNotHaveException(String id) {
        super("Shop with ID " + id + " does not exist!");
    }
}
