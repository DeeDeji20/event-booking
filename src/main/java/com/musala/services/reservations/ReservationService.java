package com.musala.services.reservations;


import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.ReservationResponse;
import com.musala.models.Event;

import java.util.List;

public interface ReservationService {

    void createReservationFor(Event event, int ticketCount);

    List<ReservationResponse> listReservations(Integer page, Integer size);


    List<ReservationResponse> viewBookedEvent(String email, Integer page, Integer size);

    ApiResponse<ReservationResponse> cancelReservation(Long id);

    ReservationResponse getReservationBy(Long id);



}
