package com.s21.devops.sample.bookingservice.Service;

import com.s21.devops.sample.bookingservice.Communication.ChargeBalanceReq;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class LoyaltyServiceImplementation implements LoyaltyService {
    private final RestTemplate restTemplate;

    public LoyaltyServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "loyaltyService", fallbackMethod = "fallbackGetLoyaltyBalance")
    public Object getLoyaltyBalance(UUID userUid) {
        String url = "http://loyalty-service/api/v1/loyalty/" + userUid;
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    @CircuitBreaker(name = "loyaltyService", fallbackMethod = "fallbackChargeBalance")
    public Object chargeBalance(UUID userUid, ChargeBalanceReq chargeBalanceReq) {
        String url = "http://loyalty-service/api/v1/loyalty/" + userUid + "/charge";
        return restTemplate.postForObject(url, chargeBalanceReq, Object.class);
    }

    public Object fallbackGetLoyaltyBalance(UUID userUid, Exception e) {
        return Map.of(
            "error", "Loyalty service unavailable",
            "userUid", userUid
        );
    }

    public Object fallbackChargeBalance(UUID userUid, ChargeBalanceReq chargeBalanceReq, Exception e) {
        return Map.of(
            "error", "Loyalty service unavailable",
            "userUid", userUid,
            "amount", chargeBalanceReq.getAmount()
        );
    }
}