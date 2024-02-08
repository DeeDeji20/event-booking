package com.musala.eventBooking.services;

import com.musala.eventBooking.dtos.request.EventCreationRequest;
import com.musala.eventBooking.dtos.response.EventResponse;

public interface EventService {
    EventResponse createEvent(EventCreationRequest eventCreationRequest);
}
