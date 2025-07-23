package com.s21.devops.sample.bookingservice.Repository;

import com.s21.devops.sample.bookingservice.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationUid(UUID reservationUid);
}