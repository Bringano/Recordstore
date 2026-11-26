package se.yrgo.orderservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.yrgo.orderservice.controllers.responses.ReceiptResponse;
import se.yrgo.orderservice.domain.receipts.Receipt;
import se.yrgo.orderservice.services.receipts.ReceiptService;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/{orderNumber}")
    public ResponseEntity<ReceiptResponse> createReceipt(@PathVariable String orderNumber) {
        Receipt receipt = receiptService.createReceipt(orderNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReceiptResponse.from(receipt));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReceiptResponse>> getMyReceipts(@AuthenticationPrincipal Jwt jwt) {
        Long userId = Long.valueOf(jwt.getSubject());
        List<ReceiptResponse> receipts = receiptService.getReceiptsByUser(userId)
                .stream()
                .map(ReceiptResponse::from)
                .toList();
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<ReceiptResponse> getReceipt(@PathVariable String orderNumber) {
        Receipt receipt = receiptService.getReceiptByOrderNumber(orderNumber);
        return ResponseEntity.ok(ReceiptResponse.from(receipt));
    }
}
