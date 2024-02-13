package com.musala.services;

import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.ReservationResponse;
import com.musala.models.Event;
import com.musala.models.Reservation;
import com.musala.models.User;
import com.musala.models.enums.Authority;
import com.musala.models.enums.Category;
import com.musala.models.enums.EventStatus;
import com.musala.models.enums.ReservationStatus;
import com.musala.services.reservations.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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


    @Test
    @Sql(scripts = {"/db/insert.sql"})
    void getReservationFor(){
        Event event = new Event();
        event.setId(5L);
        event.setEventDate(LocalDateTime.of(2024, 2, 12, 10, 0, 0));
        event.setAvailableAttendeesCount(50);
        event.setEventStatus(EventStatus.ACTIVE);
        event.setCategory(Category.GAME);
        event.setName("dev games");
        event.setCurrentNumberOfAttendees(100);
        event.setDeclinedCount(0);
        List<Reservation> list = reservationService.getReservationsFor(event);
        assertThat(list).isNotNull();
    }
}