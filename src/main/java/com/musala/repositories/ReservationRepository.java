package com.musala.repositories;

import com.musala.models.Reservation;
import com.musala.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findReservationByUser(User user, Pageable pageable);
}
