package com.musala.services;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.TicketRequest;
<<<<<<< HEAD
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.models.enums.Category;
=======
import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.security.services.JwtService;
>>>>>>> in-development
import com.musala.services.events.EventService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
<<<<<<< HEAD

=======
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
>>>>>>> in-development
import java.time.LocalDateTime;
import java.util.List;

import static com.musala.models.enums.Category.GAME;
<<<<<<< HEAD
=======
import static com.musala.models.enums.EventStatus.ENDED;
>>>>>>> in-development
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
<<<<<<< HEAD
class EventServiceTest {
    private EventCreationRequest eventCreationRequest;


    @Autowired
    private EventService eventService;

    @BeforeEach
    void setup(){
=======
@Sql(scripts = {"/db/data.sql"})
class EventServiceTest {
    private EventCreationRequest eventCreationRequest;

    @Autowired
    private EventService eventService;
    @Autowired
    private JwtService jwtService;
    private String authHeader = "Bearer ";
    @BeforeEach
    void setup(){
        String authHeader = jwtService.generateTokenFor("test@email.com");
>>>>>>> in-development
        eventCreationRequest = EventCreationRequest.builder()
                .name("John Doe")
                .date(LocalDateTime.now())
                .category("GAME")
                .availableAttendeesCount(100)
                .description("Test event")
                .build();
    }

    @Test
    void testCreateEvent() {
<<<<<<< HEAD
        String authHeader = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZW1haWwuY29tIiwiZXhwIjoxNzA3NjYwNzgxLCJpYXQiOjE3MDc1NzQzODF9.xh37ePeAJwsxPe91l6o9jVsvh0_cwnf1pn9OnwEgoPTevRt1vGYfFmdSm_5l7jtw0Z-2i504hSJX092wURSeFQ";
        EventResponse response = eventService.createEvent(eventCreationRequest, authHeader);
=======
        authHeader += jwtService.generateTokenFor("deolaoladeji@gmail.com");
        ApiResponse<EventResponse> response = eventService.createEvent(eventCreationRequest, authHeader);
>>>>>>> in-development
        assertNotNull(response);
    }


    @Test
    public void testReserveEvent(){
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setAttendeesCount(10);
<<<<<<< HEAD
        EventResponse event = eventService.getEventBy(1L);
        int numberOfAttendees = event.getAvailableAttendeesCount();
        TicketResponse ticketResponse = eventService.bookEvent(1L, ticketRequest);
        assertThat(ticketResponse).isNotNull();

        event = eventService.getEventBy(1L);

        assertThat(event.getAvailableAttendeesCount()).isEqualTo(numberOfAttendees + ticketRequest.getAttendeesCount());
    }


    @Test
    public void testSearchForEventsByName(){
        String name = "Game Time";
        Category category = GAME;
        List<EventResponse> foundEvents = eventService.searchForEvents(name, null, null, null, 1, 10);
        assertThat(foundEvents.size()).isEqualTo(1);
    }

    @Test
    void testFindAvailableEventByCategory(){
        List<EventResponse> eventResponses = eventService.searchForEvents(null, null, null, GAME, 1, 10);
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1);
=======
        EventResponse event = eventService.getEventBy(100L);
        int numberOfAttendees = event.getCurrentNumberOfAttendees();
        ApiResponse<TicketResponse> ticketResponse = eventService.bookEvent(100L, ticketRequest);
        assertThat(ticketResponse).isNotNull();

        event = eventService.getEventBy(100L);

        assertThat(event.getCurrentNumberOfAttendees()).isEqualTo(numberOfAttendees + ticketRequest.getAttendeesCount());
>>>>>>> in-development
    }

    @Test
    void testFindAvailableEventByStartDateAndEndDate(){
<<<<<<< HEAD
        LocalDateTime start = LocalDateTime.of(2024, 2, 15, 10, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 16, 10, 0, 0);
        List<EventResponse> eventResponses = eventService.searchForEvents(null, start, end, null, 1, 10);
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1);
=======
        LocalDateTime start = LocalDateTime.of(2024, 3, 15, 9, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 3, 16, 10, 0, 0);
        ApiResponse<List<EventResponse>> eventResponses = eventService.searchForEvents("dev buzz", start, end, GAME, 1, 10);
        assertNotNull(eventResponses);
        assertThat(eventResponses.getData().size()).isEqualTo(1);
>>>>>>> in-development
    }

    @Test
    public void testGetEventById(){
<<<<<<< HEAD
        EventResponse event = eventService.getEventBy(1L);
=======
        EventResponse event = eventService.getEventBy(100L);
>>>>>>> in-development
        assertThat(event).isNotNull();

    }

<<<<<<< HEAD
=======
    @Test
    public void testGetEventByDay(){
        List<EventResponse> events = eventService.getAllEventsFor(LocalDate.of(2024, 3, 15));
        assertThat(events.size()).isEqualTo(5);
    }


    @Test
    public void testDisableEventsByDate(){
        LocalDate date = LocalDate.of(2024, 1, 18);
        eventService.disableEventsFor(date);
        var events = eventService.getAllEventsFor(date);
        events.forEach(eventResponse -> assertThat(eventResponse.getEventStatus()).isEqualTo(ENDED));
    }

>>>>>>> in-development

}