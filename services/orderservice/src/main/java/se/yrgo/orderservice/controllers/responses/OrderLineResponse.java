package se.yrgo.orderservice.controllers.responses;

import java.math.BigDecimal;

import se.yrgo.orderservice.domain.orders.OrderLine;

public record OrderLineResponse(
                Long productId,
                String productName,
                int quantity,
                BigDecimal price) {

        public static OrderLineResponse from(OrderLine orderLine) {
                return new OrderLineResponse(
                                orderLine.getProductId(),
                                orderLine.getProductName(),
                                orderLine.getQuantity(),
                                orderLine.getPrice());
        }
}
