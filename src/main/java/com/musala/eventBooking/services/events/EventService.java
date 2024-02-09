package com.musala.eventBooking.services.events;

import com.musala.eventBooking.dtos.request.EventCreationRequest;
import com.musala.eventBooking.dtos.response.EventResponse;
import com.musala.eventBooking.models.User;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EventService {
    EventResponse createEvent(EventCreationRequest eventCreationRequest, User user);


    List<EventResponse> findAvailableEventsBy(String criteria, PageRequest pageable);
}
