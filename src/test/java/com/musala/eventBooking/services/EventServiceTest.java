package com.musala.eventBooking.services;

import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.EventSearchRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.dtos.response.EventResponse;
import com.musala.dtos.response.TicketResponse;
import com.musala.models.User;
import com.musala.models.enums.Authority;
import com.musala.models.enums.Category;
import com.musala.services.events.EventService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.musala.models.enums.Category.GAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EventServiceTest {
    private EventCreationRequest eventCreationRequest;
    private EventSearchRequest eventSearchRequest = new EventSearchRequest();


    @Autowired
    private EventService eventService;

    @BeforeEach
    void setup(){
        eventCreationRequest = EventCreationRequest.builder()
                .name("John Doe")
                .date(LocalDateTime.now())
                .category("GAME")
                .availableAttendeesCount(100)
                .description("Test event")
                .build();

//        eventSearchRequest.setName("game");
//        eventSearchRequest.setCategory("GAME");
//        eventSearchRequest.setStartDate("2024-02-10");
//        eventSearchRequest.setEndDate("2024-02-15");
        eventSearchRequest.setPage(1);
        eventSearchRequest.setSize(15);
    }

    @Test
    void testCreateEvent() {
        String authHeader = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZW1haWwuY29tIiwiZXhwIjoxNzA3NjYwNzgxLCJpYXQiOjE3MDc1NzQzODF9.xh37ePeAJwsxPe91l6o9jVsvh0_cwnf1pn9OnwEgoPTevRt1vGYfFmdSm_5l7jtw0Z-2i504hSJX092wURSeFQ";
        EventResponse response = eventService.createEvent(eventCreationRequest, authHeader);
        assertNotNull(response);
    }


    @Test
    void testFindAvailableEventByName(){
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy(eventSearchRequest);
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(2L);
    }

    @Test
    void testFindAvailableEventByName_caseInsensitive(){
        eventSearchRequest.setName("game");
        System.out.println(eventSearchRequest);
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy(eventSearchRequest);
        System.out.println(eventResponses);
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1L);
    }




    //TODO: add test cases for edge cases
    @Test
    public void testReserveEvent(){
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setAttendeesCount(10);
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
        List<EventResponse> foundEvents = eventService.searchForEvents(name, null, null, null);
        assertThat(foundEvents.size()).isEqualTo(1);
    }

    @Test
    void testFindAvailableEventByCategory(){
        List<EventResponse> eventResponses = eventService.searchForEvents(null, null, null, GAME);
        System.out.println(eventResponses);
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1);
    }

    @Test
    void testFindAvailableEventByStartDate(){
        LocalDateTime start = LocalDateTime.of(2024, 2, 15, 10, 0, 0);
//        LocalDateTime end = LocalDateTime.of(2024, 2, 16, 10, 0, 0);
        List<EventResponse> eventResponses = eventService.searchForEvents(null, start, null, null);
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1);
    }

    @Test
    void testFindAvailableEventsByDate(){
        LocalDateTime end = LocalDateTime.of(2024, 2, 16, 10, 0, 0);
        List<EventResponse> eventResponses = eventService.searchForEvents(null, null , end, null);
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1L);
    }

    @Test
    public void testGetEventById(){
        EventResponse event = eventService.getEventBy(1L);
        assertThat(event).isNotNull();

    }

    @Test//failed
    void testFindUnavailableEventsGivesEmptyResponse(){
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy(eventSearchRequest);
        assertThat(eventResponses.size()).isLessThan(1);
    }


}