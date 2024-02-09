package com.musala.eventBooking.services.reservations;


import com.musala.eventBooking.dtos.response.EventReservationResponse;
import com.musala.eventBooking.dtos.response.ReservationList;
import org.springframework.data.domain.PageRequest;

public interface ReservationService {
    EventReservationResponse bookReservation(Long eventId);

    ReservationList listReservations(PageRequest pageRequest);
}
