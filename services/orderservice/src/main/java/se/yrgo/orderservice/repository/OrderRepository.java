package se.yrgo.orderservice.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import se.yrgo.orderservice.domain.orders.*;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByStatus(OrderStatus status);
}
