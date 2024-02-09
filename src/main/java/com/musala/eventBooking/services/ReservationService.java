package com.musala.eventBooking.services;


import com.musala.eventBooking.dtos.response.EventReservationResponse;

public interface ReservationService {
    EventReservationResponse bookReservation(Long eventId);
}
