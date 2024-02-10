package com.musala.eventBooking.services;

import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.EventReservationResponse;
import com.musala.dtos.response.ReservationResponse;
import com.musala.exception.ConflictException;
import com.musala.models.Event;
import com.musala.models.User;
import com.musala.models.enums.Authority;
import com.musala.models.enums.ReservationStatus;
import com.musala.services.reservations.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    User user = new User();
    @BeforeEach
    void setup(){
        user.setId(1L);
        user.setEmail("test@email.com");
        user.setAuthorities(Set.of(Authority.USER));
    }

    @Test
    @Sql(scripts = {"/db/insert.sql"})
    public void testToGetReservation(){
        ReservationResponse response = reservationService.getReservationBy(101L);
        assertThat(response).isNotNull();
    }

    @Test
    @Sql(scripts = {"/db/insert.sql"})
    void testToViewUsersBookedEvent(){
        List<ReservationResponse> response =reservationService.viewBookedEvent("test@email.com", 1, 15);
        assertThat(response.size()).isEqualTo(5L);
    }

    @Test
    @Sql(scripts = {"/db/insert.sql"})
    public void testThatReservationCanBeCanceled(){
        ApiResponse<ReservationResponse> response = reservationService.cancelReservation(100L);
        assertThat(response.getData()).isNotNull();
        assertThat(reservationService.getReservationBy(100L)
                .getReservationStatus()).isEqualTo(ReservationStatus.CANCELED);
    }

}