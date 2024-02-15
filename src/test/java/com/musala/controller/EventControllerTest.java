package com.musala.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.TicketRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser
    public void createEvent() throws Exception {
        String authHeader = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZW1haWwuY29tIiwiZXhwIjoxNzA3NjYwNzgxLCJpYXQiOjE3MDc1NzQzODF9.xh37ePeAJwsxPe91l6o9jVsvh0_cwnf1pn9OnwEgoPTevRt1vGYfFmdSm_5l7jtw0Z-2i504hSJX092wURSeFQ";
        var eventCreationRequest = EventCreationRequest.builder()
                .name("John Doe")
                .date(LocalDateTime.now())
                .category("GAME")
                .availableAttendeesCount(100)
                .description("Test event")
                .build();

        mockMvc.perform(post("/events")
               .header(AUTHORIZATION, authHeader)
               .contentType(APPLICATION_JSON_VALUE)
               .content(mapper.writeValueAsBytes(eventCreationRequest)))
               .andExpect(status().is2xxSuccessful())
               .andDo(print());
    }


    @Test
    @WithMockUser
    public void reserveTicketTest() throws Exception {
        String authHeader = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZW1haWwuY29tIiwiZXhwIjoxNzA3NjYwNzgxLCJpYXQiOjE3MDc1NzQzODF9.xh37ePeAJwsxPe91l6o9jVsvh0_cwnf1pn9OnwEgoPTevRt1vGYfFmdSm_5l7jtw0Z-2i504hSJX092wURSeFQ";
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setAttendeesCount(10);
        mockMvc.perform(post("/events/1/tickets")
                .header(AUTHORIZATION, authHeader)
                .content(mapper.writeValueAsBytes(ticketRequest))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}