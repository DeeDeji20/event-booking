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
import static com.musala.models.enums.EventStatus.ENDED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
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
        authHeader += jwtService.generateTokenFor("deolaoladeji@gmail.com");
        ApiResponse<EventResponse> response = eventService.createEvent(eventCreationRequest, authHeader);
        assertNotNull(response);
    }


    @Test
    public void testReserveEvent(){
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setAttendeesCount(10);
        EventResponse event = eventService.getEventBy(100L);
        int numberOfAttendees = event.getCurrentNumberOfAttendees();
        ApiResponse<TicketResponse> ticketResponse = eventService.bookEvent(100L, ticketRequest);
        assertThat(ticketResponse).isNotNull();

        event = eventService.getEventBy(100L);

        assertThat(event.getCurrentNumberOfAttendees()).isEqualTo(numberOfAttendees + ticketRequest.getAttendeesCount());
    }

    @Test
    void testFindAvailableEventByStartDateAndEndDate(){
        LocalDateTime start = LocalDateTime.of(2024, 3, 15, 9, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 3, 16, 10, 0, 0);
        ApiResponse<List<EventResponse>> eventResponses = eventService.searchForEvents("dev buzz", start, end, GAME, 1, 10);
        assertNotNull(eventResponses);
        assertThat(eventResponses.getData().size()).isEqualTo(1);
    }

    @Test
    public void testGetEventById(){
        EventResponse event = eventService.getEventBy(100L);
        assertThat(event).isNotNull();

    }

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


}