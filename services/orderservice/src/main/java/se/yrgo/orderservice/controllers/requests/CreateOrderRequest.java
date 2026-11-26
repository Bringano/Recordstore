package se.yrgo.orderservice.controllers.requests;

import java.util.List;

public record CreateOrderRequest(List<OrderLineRequest> lines) {
}
