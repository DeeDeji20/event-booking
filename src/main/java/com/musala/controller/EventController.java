package com.musala.controller;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.models.enums.Category;
import com.musala.services.events.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@AllArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

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

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<?> findEventByCriteria(@RequestParam("name") String name,
                                          @RequestParam("start_date") LocalDateTime startDate,
                                          @RequestParam("end_date") LocalDateTime endDate,
                                          @RequestParam("end_date") Category category,
                                          @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer page,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "25") Integer size){
        List<EventResponse> eventResponse = eventService.searchForEvents(name, startDate, endDate, category, page, size);
        return ResponseEntity.ok(eventResponse);
    }
}
