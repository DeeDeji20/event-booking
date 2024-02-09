package com.musala.eventBooking.services.impl;

import com.musala.eventBooking.dtos.request.EventCreationRequest;
import com.musala.eventBooking.dtos.response.EventResponse;
import com.musala.eventBooking.models.Event;
import com.musala.eventBooking.models.enums.Category;
import com.musala.eventBooking.models.enums.EventStatus;
import com.musala.eventBooking.repositories.EventRepository;
import com.musala.eventBooking.services.EventService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.musala.eventBooking.models.enums.EventStatus.UPCOMING;

@Service
@AllArgsConstructor
public class DevEventService implements EventService {

    private final EventRepository eventRepository;

    private final ModelMapper mapper;

    @Override
    public EventResponse createEvent(EventCreationRequest eventCreationRequest) {
        Event event = mapper.map(eventCreationRequest, Event.class);
        event.setCategory(Category.valueOf(eventCreationRequest.getCategory().toUpperCase()));
        event.setEventStatus(UPCOMING);
//        event.setCreatedBy();
        Event savedEvent = eventRepository.save(event);
        return EventResponse.builder()
                .id(savedEvent.getId())
                .eventDate(savedEvent.getEventDate())
                .name(savedEvent.getName())
                .build();
    }
}
