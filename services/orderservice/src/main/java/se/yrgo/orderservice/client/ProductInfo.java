package se.yrgo.orderservice.client;

import java.math.BigDecimal;

public record ProductInfo(Long productId, String productName, BigDecimal price) {
}
