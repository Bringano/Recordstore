package se.yrgo.purchaseservice.domain.reciepts;

import java.math.*;
import java.time.*;
import java.util.*;

import jakarta.persistence.*;
import se.yrgo.purchaseservice.domain.orders.*;

@Entity
@Table(name = "reciepts")
public class Reciept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String recieptNumber;

    @OneToOne(optional = false)
    @JoinColumn(name = "order_id", unique = true, updatable = false)
    private Order order;

    @Column(nullable = false, updatable = false)
    private Instant issuedAt = Instant.now();

    @Column(updatable = false)
    private Long customerId;

    @Column(updatable = false)
    private String customerName;

    @Column(nullable = false, precision = 12, scale = 2, updatable = false)
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receipt_id")
    private List<ReceiptLine> lines = new ArrayList<>();

    protected Reciept() {
    }

    public Reciept(
            String recieptNumber,
            Order order,
            Instant issuedAt,
            Long customerId,
            String customerName,
            BigDecimal total,
            List<ReceiptLine> lines) {
        this.recieptNumber = recieptNumber;
        this.order = order;
        this.issuedAt = issuedAt;
        this.customerId = customerId;
        this.customerName = customerName;
        this.total = total;
        this.lines = lines;
    }

    public Long getId() {
        return id;
    }

    public String getRecieptNumber() {
        return recieptNumber;
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

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ReceiptLine> getLines() {
        return lines;
    }
}
