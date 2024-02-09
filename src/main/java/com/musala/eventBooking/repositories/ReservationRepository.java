package com.musala.eventBooking.repositories;

import com.musala.eventBooking.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
