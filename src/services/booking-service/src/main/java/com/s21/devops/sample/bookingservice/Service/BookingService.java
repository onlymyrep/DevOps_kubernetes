package com.s21.devops.sample.bookingservice.Service;

import com.s21.devops.sample.bookingservice.model.BookHotelReq;
import com.s21.devops.sample.bookingservice.model.HotelsAvailabilityRes;

public interface BookingService {
    void bookHotel(BookHotelReq request);
    HotelsAvailabilityRes getAvailability(UUID hotelUid, String from, String to);
}