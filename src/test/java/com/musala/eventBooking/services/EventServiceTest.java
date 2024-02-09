package com.musala.eventBooking.services;

import com.musala.eventBooking.dtos.request.EventCreationRequest;
import com.musala.eventBooking.dtos.response.EventResponse;
import com.musala.eventBooking.models.User;
import com.musala.eventBooking.models.enums.Authority;
import com.musala.eventBooking.services.events.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceTest {
    private EventCreationRequest eventCreationRequest;

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
    }

    @Test
    void testCreateEvent() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@email.com");
        user.setAuthorities(Set.of(Authority.USER));
        EventResponse response = eventService.createEvent(eventCreationRequest, user);
        assertNotNull(response);
    }


    @Test
    void testFindAvailableEventByName(){
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy("time",  PageRequest.of(0, 15, Sort.Direction.DESC, "id"));
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(2L);
    }

    @Test
    void testFindAvailableEventByName_caseInsensitive(){
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy("TIME",  PageRequest.of(0, 15, Sort.Direction.DESC, "id"));
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(2L);
    }

    @Test
    void testFindAvailableEventByCategory(){
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy("GAME",  PageRequest.of(0, 15, Sort.Direction.DESC, "id"));
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1L);
    }

    @Test
    void testFindAvailableEventByCategory_caseInsensitive(){
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy("game",  PageRequest.of(0, 15, Sort.Direction.DESC, "id"));
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1L);
    }

    @Test
    void testFindAvailableEventsByDate(){
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy("2024-01-15",  PageRequest.of(0, 15, Sort.Direction.DESC, "id"));
        assertNotNull(eventResponses);
        assertThat(eventResponses.size()).isEqualTo(1L);
    }

    @Test
    void testFindUnavailableEventsGivesEmptyResponse(){
        List<EventResponse> eventResponses = eventService.findAvailableEventsBy("2024-01-06",  PageRequest.of(0, 15, Sort.Direction.DESC, "id"));
        assertThat(eventResponses.size()).isLessThan(1);
    }
}