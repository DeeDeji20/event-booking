package com.musala.repositories;

import com.musala.models.Event;
import com.musala.models.Reservation;
import com.musala.models.User;
import com.musala.models.enums.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findReservationByUser(User user, Pageable pageable);

    List<Reservation> findReservationByEventAndReservationStatus(Event event, ReservationStatus reservationStatus);
}
