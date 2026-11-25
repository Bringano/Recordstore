package se.yrgo.orderservice.services.orders;

import java.util.*;

import org.springframework.stereotype.*;

import se.yrgo.orderservice.domain.orders.*;
import se.yrgo.orderservice.repository.*;
import se.yrgo.orderservice.services.orders.exceptions.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Long customerId) {
        return orderRepository.save(new Order(customerId));
    }

    public Order addLine(Long orderId, OrderLine line) {
        Order order = getOrder(orderId);
        order.addLine(line);
        return orderRepository.save(order);
    }

    public Order confirm(Long orderId) {
        Order order = getOrder(orderId);
        order.confirm();
        return orderRepository.save(order);
    }

    public Order cancel(Long orderId) {
        Order order = getOrder(orderId);
        order.cancel();
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order " + orderId + " not found"));
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
