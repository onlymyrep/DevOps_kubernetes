package com.s21.devops.sample.bookingservice.Service;

import com.s21.devops.sample.bookingservice.Exception.NotFoundException;
import com.s21.devops.sample.bookingservice.Model.Reservation;
import com.s21.devops.sample.bookingservice.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingServiceImplementation implements BookingService {
    private final ReservationRepository reservationRepository;

    public BookingServiceImplementation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation getBooking(String bookingUid) {
        return reservationRepository.findByReservationUid(UUID.fromString(bookingUid))
                .orElseThrow(() -> new NotFoundException("Booking not found"));
    }
}