package com.musala.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.dtos.request.UserRegistrationRequest;
import com.musala.security.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;
    private final ObjectMapper mapper = new ObjectMapper();
    private String token;



    @BeforeEach
    public void setUp(){
        token =jwtService.generateTokenFor("deolaoladeji@gmail.com");
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testCreateUser() throws Exception {
        var eventCreationRequest = UserRegistrationRequest.builder()
                .name("John Doe")
                .email("johndoe@gmail.com")
                .password("password")
                .build();

        mockMvc.perform(post("/users")
                        .header(AUTHORIZATION, "Bearer "+token)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(eventCreationRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}
