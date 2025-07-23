package com.s21.devops.sample.bookingservice.Service;

import java.util.UUID;

public interface HotelService {
    Object getHotel(UUID hotelUid);
    Object getHotelCapacity(UUID hotelUid);
}