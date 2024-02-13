package com.musala.services.events;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.models.enums.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    ApiResponse<EventResponse> createEvent(EventCreationRequest eventCreationRequest, String authorizationHeader);

    ApiResponse<List<EventResponse>> searchForEvents(String name, LocalDateTime startDate, LocalDateTime endDate, Category category, Integer page, Integer size);

    EventResponse getEventBy(Long id);

    ApiResponse<TicketResponse> bookEvent(Long eventId, TicketRequest ticketRequest);


    List<EventResponse> getAllEventsFor(LocalDate date);
}
