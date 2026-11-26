package se.yrgo.orderservice.controllers.requests;

public record OrderLineRequest(Long productId, int quantity) {
}
