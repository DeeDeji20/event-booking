package com.musala.controller;

import com.musala.dtos.response.ReservationList;
import com.musala.dtos.response.ReservationResponse;
import com.musala.security.services.JwtService;
import com.musala.services.reservations.ReservationService;
import com.musala.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<?> getAllReservations(
            @RequestParam(name = "page", value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size",value = "size", required = false, defaultValue = "25") Integer size){
        List<ReservationResponse> reservations = reservationService.listReservations(page, size);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findUsersReservations(@PathVariable String email,
            @RequestParam(name = "page", value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size",value = "size", required = false, defaultValue = "25") Integer size){
        return ResponseEntity.ok(reservationService.viewBookedEvent(email, page, size));
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }
}
