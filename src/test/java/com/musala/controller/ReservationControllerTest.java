package com.musala.controller;

import com.musala.security.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;
    private String token;



    @BeforeEach
    public void setUp(){
        token =jwtService.generateTokenFor("deolaoladeji@gmail.com");
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void cancelReservation() throws Exception {
        mockMvc.perform(post("/api/v1/reservation/cancel/100")
                        .header(AUTHORIZATION, "Bearer"+ token))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }


    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void viewUsersReservations() throws Exception {
        mockMvc.perform(get("/api/v1/reservation/test@email.com")
                        .header(AUTHORIZATION, "Bearer"+ token))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    @WithMockUser(username = "john", authorities = {"USER"})
    public void findAllReservations() throws Exception {
        mockMvc.perform(get("/api/v1/reservation?page=1&size=10")
                        .header("AUTHORIZATION", "Bearer "+token))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }



}
