package com.s21.devops.sample.bookingservice.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class HotelServiceImplementation implements HotelService {
    private final RestTemplate restTemplate;

    public HotelServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "hotelService", fallbackMethod = "fallbackGetHotel")
    public Object getHotel(UUID hotelUid) {
        String url = "http://hotel-service/api/v1/hotels/" + hotelUid;
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    @CircuitBreaker(name = "hotelService", fallbackMethod = "fallbackGetHotelCapacity")
    public Object getHotelCapacity(UUID hotelUid) {
        String url = "http://hotel-service/api/v1/hotels/" + hotelUid + "/capacity";
        return restTemplate.getForObject(url, Object.class);
    }

    public Object fallbackGetHotel(UUID hotelUid, Exception e) {
        return Map.of(
            "error", "Hotel service unavailable",
            "hotelUid", hotelUid
        );
    }

    public Object fallbackGetHotelCapacity(UUID hotelUid, Exception e) {
        return Map.of(
            "error", "Hotel service unavailable",
            "hotelUid", hotelUid
        );
    }
}