package com.musala.eventBooking.controller;

import com.musala.eventBooking.dtos.response.ReservationList;
import com.musala.eventBooking.services.reservations.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ResponseEntity<?> book(){
        return null;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllReservations(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "25") Integer pageSize
    ){
        if (pageNumber < 0) pageNumber = 1;
        if (pageSize < 1) pageNumber=1;

        ReservationList reservations = reservationService.listReservations(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(reservations);
    }
}
