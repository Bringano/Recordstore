package se.yrgo.orderservice.domain.receipts;

import java.math.*;
import java.time.*;
import java.util.*;

import jakarta.persistence.*;
import se.yrgo.orderservice.domain.orders.*;

@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String receiptNumber;

    @OneToOne(optional = false)
    @JoinColumn(name = "order_id", unique = true, updatable = false)
    private Order order;

    @Column(nullable = false, updatable = false)
    private Instant issuedAt = Instant.now();

    @Column(updatable = false)
    private Long customerId;

    @Column(nullable = false, precision = 12, scale = 2, updatable = false)
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receipt_id")
    private List<ReceiptLine> lines = new ArrayList<>();

    protected Receipt() {
    }

    public Receipt(
            String receiptNumber,
            Order order,
            Long customerId,
            BigDecimal total) {
        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("The order must be confirmed to create a receipt");
        }
        this.receiptNumber = receiptNumber;
        this.order = order;
        this.customerId = customerId;
        this.total = total;
    }

    public void addLine(ReceiptLine newReceiptLine) {
        lines.add(newReceiptLine);
    }

    public Long getId() {
        return id;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public Order getOrder() {
        return order;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ReceiptLine> getLines() {
        return lines;
    }
}
