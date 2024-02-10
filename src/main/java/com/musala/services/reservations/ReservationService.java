package com.musala.services.reservations;


import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.ReservationList;
import com.musala.dtos.response.ReservationResponse;
import com.musala.models.Event;
import com.musala.models.User;

import java.util.List;

public interface ReservationService {

    void createReservationFor(Event event, int ticketCount);

    ReservationList listReservations(Integer page, Integer size);


    List<ReservationResponse> viewBookedEvent(String email, Integer page, Integer size);

    ApiResponse<ReservationResponse> cancelReservation(Long id);

    ReservationResponse getReservationBy(Long id);



}
