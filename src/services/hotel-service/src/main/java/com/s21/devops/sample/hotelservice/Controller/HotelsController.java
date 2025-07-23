package com.s21.devops.sample.hotelservice;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelsController {
    
    @GetMapping
    public List<HotelInfoRes> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{hotelUid}")
    public HotelInfoRes getHotel(@PathVariable UUID hotelUid) {
        return hotelService.getHotel(hotelUid);
    }
}