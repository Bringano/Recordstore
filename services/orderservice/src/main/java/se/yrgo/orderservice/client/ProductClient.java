package se.yrgo.orderservice.client;

public interface ProductClient {
    ProductInfo getProduct(Long productId);
}
