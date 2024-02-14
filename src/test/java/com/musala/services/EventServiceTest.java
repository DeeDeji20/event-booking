package com.musala.services;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.models.enums.Category;
import com.musala.security.services.JwtService;
import com.musala.services.events.EventService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.musala.models.enums.Category.GAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class EventServiceTest {
    private EventCreationRequest eventCreationRequest;

    @Autowired
    private EventService eventService;
    @Autowired
    private JwtService jwtService;
    private String authHeader;
    @BeforeEach
    void setup(){
        String authHeader = jwtService.generateTokenFor("test@email.com");
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
        String authHeader = jwtService.generateTokenFor("test@email.com");
        ApiResponse<EventResponse> response = eventService.createEvent(eventCreationRequest, authHeader);
        assertNotNull(response);
    }


    @Test
    public void testReserveEvent(){
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setAttendeesCount(10);
        EventResponse event = eventService.getEventBy(1L);
        int numberOfAttendees = event.getAvailableAttendeesCount();
        ApiResponse<TicketResponse> ticketResponse = eventService.bookEvent(1L, ticketRequest);
        assertThat(ticketResponse).isNotNull();

        event = eventService.getEventBy(1L);

        assertThat(event.getAvailableAttendeesCount()).isEqualTo(numberOfAttendees + ticketRequest.getAttendeesCount());
    }


    @Test
    public void testSearchForEventsByName(){
        String name = "Game Time";
        Category category = GAME;
        ApiResponse<List<EventResponse>> foundEvents = eventService.searchForEvents(name, null, null, null, 1, 10);
        assertThat(foundEvents.getData().size()).isEqualTo(1);
    }

    @Test
    @Sql(scripts = "/db/data.sql")
    void testFindAvailableEventByCategory(){
        ApiResponse<List<EventResponse>> eventResponses = eventService.searchForEvents("First Game", null, null, GAME, 1, 10);
        assertNotNull(eventResponses);
        assertThat(eventResponses.getData().size()).isEqualTo(4);
    }

    @Test
    void testFindAvailableEventByStartDateAndEndDate(){
        LocalDateTime start = LocalDateTime.of(2024, 2, 15, 10, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 16, 10, 0, 0);
        ApiResponse<List<EventResponse>> eventResponses = eventService.searchForEvents(null, start, end, null, 1, 10);
        assertNotNull(eventResponses);
        assertThat(eventResponses.getData().size()).isEqualTo(1);
    }

    @Test
    public void testGetEventById(){
        EventResponse event = eventService.getEventBy(1L);
        assertThat(event).isNotNull();

    }

    @Test
    public void testFetEventByDay(){
        List<EventResponse> events = eventService.getAllEventsFor(LocalDate.of(2024, 2, 15));
        assertThat(events.size()).isEqualTo(1);
    }


}