package com.musala.services.reservations;


import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.EventReservationResponse;
import com.musala.dtos.response.ReservationList;
import com.musala.dtos.response.ReservationResponse;

import java.util.List;

public interface ReservationService {
    EventReservationResponse bookReservation(Long eventId, long user);

    ReservationList listReservations(Integer page, Integer size);


    List<ReservationResponse> viewBookedEvent(String email, Integer page, Integer size);

    ApiResponse<ReservationResponse> cancelReservation(Long id);

    ReservationResponse getReservationBy(Long id);
}
