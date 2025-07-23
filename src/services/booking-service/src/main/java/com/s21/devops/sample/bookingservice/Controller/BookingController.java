package com.s21.devops.sample.bookingservice.controller;

import com.s21.devops.sample.bookingservice.model.BookHotelReq;
import com.s21.devops.sample.bookingservice.model.HotelsAvailabilityRes;
import com.s21.devops.sample.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void bookHotel(@Valid @RequestBody BookHotelReq request) {
        bookingService.bookHotel(request);
    }

    @GetMapping("/{hotelUid}/availability")
    public HotelsAvailabilityRes getAvailability(
            @PathVariable UUID hotelUid,
            @RequestParam String from,
            @RequestParam String to) {
        return bookingService.getAvailability(hotelUid, from, to);
    }
}