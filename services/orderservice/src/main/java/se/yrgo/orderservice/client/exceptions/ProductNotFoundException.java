package se.yrgo.orderservice.client.exceptions;

/**
 * ProductNotFoundException
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
