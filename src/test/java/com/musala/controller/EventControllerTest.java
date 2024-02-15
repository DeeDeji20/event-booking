package com.musala.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.dtos.request.EventCreationRequest;
import com.musala.dtos.request.TicketRequest;
import com.musala.security.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;

<<<<<<< HEAD
=======
import static com.musala.models.enums.Category.GAME;
>>>>>>> in-development
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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


    @Autowired
    private JwtService jwtService;
    private String token;


    @BeforeEach
    public void setUp(){
        token =jwtService.generateTokenFor("deolaoladeji@gmail.com");
    }

    @Test
    public void createEventTest() throws Exception {
        var eventCreationRequest = EventCreationRequest.builder()
                .name("test")
                .date(LocalDateTime.now())
                .category("GAME")
                .availableAttendeesCount(100)
                .description("Test event")
                .build();

        mockMvc.perform(post("/events")
               .header(AUTHORIZATION, "Bearer "+token)
               .contentType(APPLICATION_JSON_VALUE)
               .content(mapper.writeValueAsBytes(eventCreationRequest)))
               .andExpect(status().is2xxSuccessful())
               .andDo(print());
    }


    @Test
    @WithMockUser
    public void reserveTicketTest() throws Exception {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setAttendeesCount(10);
        mockMvc.perform(post("/events/100/tickets")
                .header(AUTHORIZATION, "Bearer "+token)
                .content(mapper.writeValueAsBytes(ticketRequest))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void getTicketsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events")
                        .header(AUTHORIZATION, "Bearer "+token)
                        .param("name", "dev games")
                        .param("start_date", LocalDateTime.of(2024, 3, 15, 10, 0, 0).toString())
                        .param("end_date", LocalDateTime.of(2024, 3, 16, 10, 0, 0).toString())
                        .param("category", GAME.toString())
                        .param("page", String.valueOf(1))
                        .param("size", String.valueOf(10))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}