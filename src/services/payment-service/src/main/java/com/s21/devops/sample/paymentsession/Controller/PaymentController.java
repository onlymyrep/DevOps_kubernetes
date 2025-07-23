package com.s21.devops.sample.paymentservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    
    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public void processPayment(@RequestBody PayReq request) {
        paymentService.processPayment(request);
    }
}