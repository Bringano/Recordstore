package se.yrgo.orderservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.yrgo.orderservice.controllers.requests.CreateOrderRequest;
import se.yrgo.orderservice.controllers.responses.OrderResponse;
import se.yrgo.orderservice.domain.orders.Order;
import se.yrgo.orderservice.services.orders.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody CreateOrderRequest request) {
        Long userId = Long.valueOf(jwt.getSubject());
        Order order = service.createOrder(userId, request.lines());
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.from(order));
    }

    @PostMapping("/{orderNumber}/confirm")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable String orderNumber) {
        Order order = service.confirm(orderNumber);
        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @PostMapping("/{orderNumber}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable String orderNumber) {
        Order order = service.cancel(orderNumber);
        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = service.getAllOrders()
                .stream()
                .map(OrderResponse::from)
                .toList();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my")
    public ResponseEntity<List<OrderResponse>> getMyOrders(@AuthenticationPrincipal Jwt jwt) {
        Long userId = Long.valueOf(jwt.getSubject());
        List<OrderResponse> orders = service.getOrdersByUser(userId)
                .stream()
                .map(OrderResponse::from)
                .toList();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getByOrderNumber(@PathVariable String orderNumber) {
        Order order = service.getByOrderNumber(orderNumber);
        return ResponseEntity.ok(OrderResponse.from(order));
    }
}
