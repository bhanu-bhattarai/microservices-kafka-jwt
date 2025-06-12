package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    // Simple GET endpoint to simulate payment inquiry or status
    @GetMapping
    public String getPaymentStatus() {
        return "Payment service is operational and secure.";
    }
}