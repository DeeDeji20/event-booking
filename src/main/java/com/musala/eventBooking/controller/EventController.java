package com.musala.eventBooking.controller;

import com.musala.eventBooking.dtos.request.EventCreationRequest;
import com.musala.eventBooking.dtos.response.EventResponse;
import com.musala.eventBooking.models.User;
import com.musala.eventBooking.security.services.JwtService;
import com.musala.eventBooking.services.events.EventService;
import com.musala.eventBooking.services.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    private ResponseEntity<?> findEventBy(@PathVariable("criteria") String criteria,
                                          @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "25") Integer pageSize
    ){
        if (pageNumber < 0) pageNumber = 1;
        if (pageSize < 1) pageNumber=1;

        List<EventResponse> eventResponse = eventService.findAvailableEventsBy(criteria, PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(eventResponse);
    }
}
