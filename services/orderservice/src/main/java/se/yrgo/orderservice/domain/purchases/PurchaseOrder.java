package se.yrgo.orderservice.domain.purchases;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import se.yrgo.orderservice.util.NumberGenerator;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String purchaseOrderNumber = NumberGenerator.purchaseOrderNumber();

    @Column(nullable = false)
    private String supplier;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseStatus status = PurchaseStatus.ORDERED;

    private Instant arrivedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private List<PurchaseOrderLine> lines = new ArrayList<>();

    protected PurchaseOrder() {
    }

    public PurchaseOrder(String supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    public void addLine(PurchaseOrderLine line) {
        if (status != PurchaseStatus.ORDERED) {
            throw new IllegalStateException("Cannot modify a " + status + " purchase order");
        }
        lines.add(line);
    }

    public void markArrived() {
        if (status != PurchaseStatus.ORDERED) {
            throw new IllegalStateException("Only an ORDERED purchase order can arrive");
        }
        status = PurchaseStatus.ARRIVED;
        arrivedAt = Instant.now();
    }

    public void cancel() {
        if (status == PurchaseStatus.ARRIVED) {
            throw new IllegalStateException("Cannot cancel an arrived purchase");
        }
        status = PurchaseStatus.CANCELLED;
    }

    @SuppressWarnings("null")
    public BigDecimal getTotalCost() {
        return lines.stream()
                .map(l -> l.getUnitCost().multiply(BigDecimal.valueOf(l.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public Instant getArrivedAt() {
        return arrivedAt;
    }

    public List<PurchaseOrderLine> getLines() {
        return lines;
    }
}
