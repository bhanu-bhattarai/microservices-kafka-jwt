package org.example.controller;

import org.example.model.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderController(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // POST endpoint to place an order
    @PostMapping
    public String placeOrder() {
        // Hardcoded order with unique ID
        OrderEvent event = new OrderEvent();
        event.orderId = UUID.randomUUID().toString();
        event.status = "ORDER_CREATED";
        event.amount = 199.99;

        // Send order event to Kafka topic
        kafkaTemplate.send("order-events", event);
        return "Order placed successfully with ID: " + event.orderId;
    }
}
