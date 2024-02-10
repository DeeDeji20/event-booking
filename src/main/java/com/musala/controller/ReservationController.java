package com.musala.controller;

import com.musala.dtos.response.ReservationList;
import com.musala.security.services.JwtService;
import com.musala.services.reservations.ReservationService;
import com.musala.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<?> getAllReservations(
            @RequestParam(name = "page", value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size",value = "size", required = false, defaultValue = "25") Integer size){
        ReservationList reservations = reservationService.listReservations(page, size);
        return ResponseEntity.ok(reservations);
    }
}
