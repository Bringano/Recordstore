package se.yrgo.orderservice.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import se.yrgo.orderservice.domain.receipts.*;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<Receipt> findByOrderId(Long orderId);

    List<Receipt> findByCustomerId(Long customerId);
}
