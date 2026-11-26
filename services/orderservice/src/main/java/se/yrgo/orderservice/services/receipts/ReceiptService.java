package se.yrgo.orderservice.services.receipts;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.orderservice.domain.orders.*;
import se.yrgo.orderservice.domain.receipts.*;
import se.yrgo.orderservice.repository.*;
import se.yrgo.orderservice.services.orders.exceptions.*;
import se.yrgo.orderservice.services.receipts.exceptions.*;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final OrderRepository orderRepository;

    public ReceiptService(
            ReceiptRepository receiptRepository,
            OrderRepository orderRepository) {
        this.receiptRepository = receiptRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Receipt createReceipt(String orderNumber) {
        if (receiptRepository.findByOrderNumber(orderNumber).isPresent()) {
            throw new IllegalStateException("Receipt already exists for order " + orderNumber);
        }

        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Order " + orderNumber + " not found"));

        Receipt receipt = new Receipt(
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

    public Receipt getReceiptByOrderNumber(String orderNumber) {
        return receiptRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ReceiptNotFoundException("No receipt for order " + orderNumber));
    }

    public List<Receipt> getCustomerReceipts(Long customerId) {
        return receiptRepository.findByCustomerId(customerId);
    }
}
