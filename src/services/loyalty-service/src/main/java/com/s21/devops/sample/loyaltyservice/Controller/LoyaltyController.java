package com.s21.devops.sample.loyaltyservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/loyalty")
public class LoyaltyController {
    
    @Autowired
    private LoyaltyService loyaltyService;

    @GetMapping
    public LoyaltyBalanceRes getBalance() {
        return loyaltyService.getBalance();
    }
}