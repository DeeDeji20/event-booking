package com.musala.eventBooking.controller;

import com.musala.eventBooking.dtos.request.EventCreationRequest;
import com.musala.eventBooking.dtos.response.EventResponse;
import com.musala.eventBooking.models.User;
import com.musala.eventBooking.security.services.JwtService;
import com.musala.eventBooking.services.events.EventService;
import com.musala.eventBooking.services.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    private final JwtService jwtService;

    private final UserService userService;


    @PostMapping("")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventCreationRequest eventCreationRequest, HttpServletRequest request){
        String token = request.getHeader("Authorization");
         UserDetails userDetails = jwtService.extractUserDetailsFrom(token);
         User user = userService.getUserByEmail(userDetails.getUsername());
        EventResponse response = eventService.createEvent(eventCreationRequest, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{criteria}", produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<?> findEventBy(@PathVariable("criteria") String criteria){
        List<EventResponse> eventResponse = eventService.findEventBy(criteria);
        return ResponseEntity.ok(eventResponse);
    }
}
