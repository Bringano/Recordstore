package se.yrgo.orderservice.controllers.requests;

import java.util.List;

public record CreateOrderRequest(
        Long customerId,
        List<OrderLineRequest> lines) {
}
