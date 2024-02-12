package com.musala.services.events;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.models.enums.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    EventResponse createEvent(EventCreationRequest eventCreationRequest, String authorizationHeader);

    List<EventResponse> searchForEvents(String name, LocalDateTime startDate, LocalDateTime endDate, Category category, Integer page, Integer size);

    EventResponse getEventBy(Long id);

    TicketResponse bookEvent(Long eventId, TicketRequest ticketRequest);


    List<EventResponse> getAllEventsFor(LocalDate date);
}
