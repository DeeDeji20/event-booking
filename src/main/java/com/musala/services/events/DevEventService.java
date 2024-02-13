package com.musala.services.events;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.exception.AppException;
import com.musala.exception.NotFoundException;
import com.musala.models.Event;
import com.musala.models.User;
import com.musala.models.enums.Category;
import com.musala.repositories.EventRepository;
import com.musala.security.services.JwtService;
import com.musala.services.reservations.ReservationService;
import com.musala.services.users.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.musala.models.enums.EventStatus.UPCOMING;
import static com.musala.util.AppUtil.createPageRequestWith;

@Service
@AllArgsConstructor
@Slf4j
public class DevEventService implements EventService {

    private final EventRepository eventRepository;
    private final JwtService jwtService;
    private final UserService userService;
    private final ReservationService reservationService;
    private final ModelMapper mapper;

    @Override
    public ApiResponse<EventResponse> createEvent(EventCreationRequest eventCreationRequest, String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        UserDetails userDetails = jwtService.extractUserDetailsFrom(token);
        User user = userService.getUserByEmail(userDetails.getUsername());
        Event event = mapper.map(eventCreationRequest, Event.class);
        event.setCategory(Category.valueOf(eventCreationRequest.getCategory().toUpperCase()));
        event.setEventStatus(UPCOMING);
        event.setCreatedBy(user);
        Event savedEvent = eventRepository.save(event);
        var eventResponse = mapper.map(savedEvent, EventResponse.class);
        return new ApiResponse<>(eventResponse);
    }

    @Override
    public ApiResponse<List<EventResponse>> searchForEvents(String name, LocalDateTime startDate, LocalDateTime endDate, Category category, Integer page, Integer size) {
        PageRequest pageRequest = createPageRequestWith(page, size);
        Page<Event> events = eventRepository.findByNameOrEventDateBetweenOrCategory(name, startDate, endDate, category, pageRequest);
         List<EventResponse> eventResponses = buildEventResponses(events.getContent());
         return new ApiResponse<>(eventResponses);
    }


    @Override
    public EventResponse getEventBy(Long id) {
        Event event = findEventBy(id);
        return mapper.map(event, EventResponse.class);
    }

    private Event findEventBy(Long id) {
        return eventRepository
                        .findById(id).orElseThrow(()->new NotFoundException(String.format("Event with id %d not found", id)));
    }


    private List<EventResponse> buildEventResponses(List<Event> events){
        return events.stream().map(event -> mapper.map(event, EventResponse.class)).toList();
    }

    @Override
    public ApiResponse<TicketResponse> bookEvent(Long eventId, TicketRequest ticketRequest) {
        Event foundEvent = findEventBy(eventId);
        int numberOfTickets = ticketRequest.getAttendeesCount();
        TicketResponse response = new TicketResponse();
        if (foundEvent.getEventDate().isBefore(LocalDateTime.now())) throw new AppException("Event already happened");
        int projected = foundEvent.getCurrentNumberOfAttendees() + ticketRequest.getAttendeesCount();
        if (foundEvent.getAvailableAttendeesCount() < projected) {
            numberOfTickets = ticketRequest.getAttendeesCount() - (projected - foundEvent.getCurrentNumberOfAttendees());
            response.setMessage("System was only able to reserve "+ numberOfTickets +"slots for you as there were only that many slots left");
        }
        reserveTicket(foundEvent, numberOfTickets);
        Event savedEvent = eventRepository.save(foundEvent);
        reservationService.createReservationFor(savedEvent, ticketRequest.getAttendeesCount());
        response.setMessage("Tickets reserved successfully");
        return new ApiResponse<>(response);
    }

    @Override
    public List<EventResponse> getAllEventsFor(LocalDate date) {
        return  buildEventResponses(eventRepository.findEventByEventDate(date));

    }

    private void reserveTicket(Event foundEvent, int numberOfTickets) {
        foundEvent.setCurrentNumberOfAttendees(foundEvent.getCurrentNumberOfAttendees()+numberOfTickets);
    }
}
