package se.yrgo.recordservice.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String articleNumber;

    private BigDecimal price;
    private BigDecimal salesPrice;
    private int stockQuantity;
    private int minPackDays;
    private int maxPackDays;

    protected Product() {
    }

    protected Product(String articleNumber, BigDecimal price,
                      int stockQuantity) {
        this.articleNumber = articleNumber;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getMinPackDays() {
        return minPackDays;
    }

    public void setMinPackDays(int minPackDays) {
        this.minPackDays = minPackDays;
    }

    public int getMaxPackDays() {
        return maxPackDays;
    }

    public void setMaxPackDays(int maxPackDays) {
        this.maxPackDays = maxPackDays;
    }
}