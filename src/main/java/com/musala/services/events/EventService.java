package com.musala.services.events;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.EventSearchRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.models.User;

import java.util.List;

public interface EventService {
    EventResponse createEvent(EventCreationRequest eventCreationRequest, String authorizationHeader);


//    List<EventResponse> findAvailableEventsBy(String criteria, PageRequest pageable);

    EventResponse getEventBy(Long id);

    TicketResponse bookEvent(Long eventId, TicketRequest ticketRequest);


    List<EventResponse> findAvailableEventsBy(EventSearchRequest eventSearchRequest);
}
