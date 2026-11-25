package se.yrgo.orderservice.services.receipts;

import java.time.*;
import java.time.format.*;
import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.orderservice.domain.orders.*;
import se.yrgo.orderservice.domain.receipts.*;
import se.yrgo.orderservice.repository.*;
import se.yrgo.orderservice.services.orders.exceptions.*;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final OrderRepository orderRepository;

    private static final DateTimeFormatter DATE = DateTimeFormatter.BASIC_ISO_DATE;

    public ReceiptService(ReceiptRepository receiptRepository, OrderRepository orderRepository) {
        this.receiptRepository = receiptRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Receipt createReceipt(Long orderId) {
        if (receiptRepository.findByOrderId(orderId).isPresent()) {
            throw new IllegalStateException("Receipt already exists for order " + orderId);
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order " + orderId + " not found"));

        Receipt receipt = new Receipt(
                generateReceiptNumber(),
                order,
                order.getCustomerId(),
                order.getTotal());

        for (OrderLine line : order.getLines()) {
            receipt.addLine(new ReceiptLine(
                    line.getProductName(),
                    line.getQuantity(),
                    line.getPrice()));
        }

        receiptRepository.save(receipt);

        order.complete();

        return receipt;
    }

    public Optional<Receipt> getReceiptByOrderId(Long orderId) {
        return receiptRepository.findByOrderId(orderId);
    }

    public List<Receipt> getCustomerReceipts(Long customerId) {
        return receiptRepository.findByCustomerId(customerId);
    }

    public String generateReceiptNumber() {
        String date = DATE.format(LocalDate.now(ZoneId.systemDefault()));
        String suffix = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
        return "RCP-" + date + "-" + suffix;
    }
}
