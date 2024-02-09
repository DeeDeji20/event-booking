package com.musala.eventBooking.services.events;

import com.musala.eventBooking.dtos.request.EventCreationRequest;
import com.musala.eventBooking.dtos.response.EventResponse;
import com.musala.eventBooking.models.Event;
import com.musala.eventBooking.models.User;
import com.musala.eventBooking.models.enums.Category;
import com.musala.eventBooking.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musala.eventBooking.models.enums.EventStatus.UPCOMING;

@Service
@AllArgsConstructor
public class DevEventService implements EventService {

    private final EventRepository eventRepository;

    private final ModelMapper mapper;

    @Override
    public EventResponse createEvent(EventCreationRequest eventCreationRequest, User user) {
        Event event = mapper.map(eventCreationRequest, Event.class);
        event.setCategory(Category.valueOf(eventCreationRequest.getCategory().toUpperCase()));
        event.setEventStatus(UPCOMING);
        event.setCreatedBy(user);
        Event savedEvent = eventRepository.save(event);
        return EventResponse.builder()
                .id(savedEvent.getId())
                .eventDate(savedEvent.getEventDate())
                .name(savedEvent.getName())
                .build();
    }

    @Override
    public List<EventResponse> findEventBy(String criteria) {
//        eventRepository
        return null;
    }
}
