package se.yrgo.orderservice.services.orders;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.orderservice.client.ProductClient;
import se.yrgo.orderservice.client.ProductInfo;
import se.yrgo.orderservice.controllers.requests.OrderLineRequest;
import se.yrgo.orderservice.domain.orders.Order;
import se.yrgo.orderservice.domain.orders.OrderLine;
import se.yrgo.orderservice.repository.OrderRepository;
import se.yrgo.orderservice.services.orders.exceptions.OrderNotFoundException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(
            OrderRepository orderRepository,
            ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Transactional
    public Order createOrder(Long customerId, List<OrderLineRequest> lines) {
        Order order = new Order(customerId);
        for (OrderLineRequest line : lines) {
            ProductInfo p = productClient.getProduct(line.productId());
            order.addLine(new OrderLine(p.productId(), p.productName(), line.quantity(), p.price()));
        }
        return orderRepository.save(order);
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
