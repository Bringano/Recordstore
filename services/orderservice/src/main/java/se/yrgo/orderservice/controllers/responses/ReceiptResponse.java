package se.yrgo.orderservice.controllers.responses;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import se.yrgo.orderservice.domain.receipts.Receipt;

public record ReceiptResponse(Long id, String receiptNumber, String orderNumber,
        Instant issuedAt, Long customerId, BigDecimal total,
        List<ReceiptLineResponse> lines) {

    public static ReceiptResponse from(Receipt receipt) {
        List<ReceiptLineResponse> lines = receipt.getLines().stream()
                .map(ReceiptLineResponse::from)
                .toList();
        return new ReceiptResponse(
                receipt.getId(),
                receipt.getReceiptNumber(),
                receipt.getOrder().getOrderNumber(),
                receipt.getIssuedAt(),
                receipt.getCustomerId(),
                receipt.getTotal(),
                lines);
    }
}
