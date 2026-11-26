package se.yrgo.orderservice.controllers.responses;

import java.math.BigDecimal;

import se.yrgo.orderservice.domain.receipts.ReceiptLine;

public record ReceiptLineResponse(String productName, int quantity,
        BigDecimal unitPrice, BigDecimal lineTotal) {

    public static ReceiptLineResponse from(ReceiptLine line) {
        return new ReceiptLineResponse(
                line.getProductName(),
                line.getQuantity(),
                line.getUnitPrice(),
                line.getLineTotal());
    }
}
