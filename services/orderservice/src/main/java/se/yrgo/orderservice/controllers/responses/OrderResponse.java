package se.yrgo.orderservice.controllers.responses;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import se.yrgo.orderservice.domain.orders.Order;
import se.yrgo.orderservice.domain.orders.OrderStatus;

public record OrderResponse(Long id, String orderNumber, Long customerId,
                Instant createdAt, OrderStatus status,
                List<OrderLineResponse> lines, BigDecimal total) {
        public static OrderResponse from(Order order) {
                List<OrderLineResponse> lines = order.getLines().stream()
                                .map(OrderLineResponse::from)
                                .toList();
                return new OrderResponse(
                                order.getId(),
                                order.getOrderNumber(),
                                order.getCustomerId(),
                                order.getCreatedAt(),
                                order.getStatus(),
                                lines,
                                order.getTotal());
        }
}
