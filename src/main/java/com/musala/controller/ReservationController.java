package com.musala.controller;

import com.musala.dtos.response.ApiResponse;
import com.musala.services.reservations.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@EnableCaching
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<?> getAllReservations(
            @RequestParam(name = "page", value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size",value = "size", required = false, defaultValue = "25") Integer size){
        ApiResponse<?> reservations = reservationService.listReservations(page, size);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findUsersReservations(
            @PathVariable String email,
            @RequestParam(name = "page", value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size",value = "size", required = false, defaultValue = "25") Integer size){
        return ResponseEntity.ok(reservationService.viewBookedEvent(email, page, size));
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }
}
