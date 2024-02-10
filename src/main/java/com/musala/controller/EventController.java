package com.musala.controller;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.EventSearchRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.security.services.JwtService;
import com.musala.services.events.EventService;
import com.musala.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@AllArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    private final JwtService jwtService;

    private final UserService userService;


    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventCreationRequest eventCreationRequest, @RequestHeader(name = AUTHORIZATION) String authorizationHeader){
        EventResponse response = eventService.createEvent(eventCreationRequest, authorizationHeader);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{eventId}/tickets")
    public ResponseEntity<?> reserveTicket(@RequestBody TicketRequest ticketRequest, @PathVariable Long eventId){
        TicketResponse response = eventService.bookEvent(eventId, ticketRequest);
        return ResponseEntity.ok(response);
    }




//    @GetMapping(value = "/{criteria}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    private ResponseEntity<?> findEventBy(@PathVariable("criteria") String criteria,
//                                          @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
//                                          @RequestParam(value = "pageSize", required = false, defaultValue = "25") Integer pageSize
//    ){
//        if (pageNumber < 0) pageNumber = 1;
//        if (pageSize < 1) pageNumber=1;
//
//        List<EventResponse> eventResponse = eventService.findAvailableEventsBy(criteria, PageRequest.of(pageNumber, pageSize));
//        return ResponseEntity.ok(eventResponse);
//    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<?> findEventByV2(@RequestParam("name") String name,
                                          @RequestParam("start_date") String startDate,
                                          @RequestParam("end_date") String endDate,
                                          @RequestParam("end_date") String category,
                                          @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer page,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "25") Integer size
    ){
        if (page < 0) page = 1;
        if (size < 1) size=1;
        EventSearchRequest eventSearchRequest = new EventSearchRequest(name, category, startDate, endDate, page, size);
        List<EventResponse> eventResponse = eventService.findAvailableEventsBy(eventSearchRequest);
        return ResponseEntity.ok(eventResponse);
    }
}
