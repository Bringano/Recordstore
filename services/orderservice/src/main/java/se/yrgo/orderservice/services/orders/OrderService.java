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

    public Order addLine(String orderNumber, OrderLine line) {
        Order order = getByOrderNumber(orderNumber);
        order.addLine(line);
        return orderRepository.save(order);
    }

    public Order confirm(String orderNumber) {
        Order order = getByOrderNumber(orderNumber);
        order.confirm();
        return orderRepository.save(order);
    }

    public Order cancel(String orderNumber) {
        Order order = getByOrderNumber(orderNumber);
        order.cancel();
        return orderRepository.save(order);
    }

    public Order getByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Order " + orderNumber + " not found"));
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
