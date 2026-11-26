package se.yrgo.orderservice.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import se.yrgo.orderservice.domain.receipts.*;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<Receipt> findByReceiptNumber(String receiptNumber);

    @Query("SELECT r FROM Receipt r WHERE r.order.orderNumber = :orderNumber")
    Optional<Receipt> findByOrderNumber(@Param("orderNumber") String orderNumber);

    List<Receipt> findByCustomerId(Long customerId);
}
