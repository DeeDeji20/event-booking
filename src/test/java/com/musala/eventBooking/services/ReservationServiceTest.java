package com.musala.eventBooking.services;

import com.musala.eventBooking.dtos.response.EventReservationResponse;
import com.musala.eventBooking.exception.AppException;
import com.musala.eventBooking.exception.ConflictException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    void testBookReservation() {
        EventReservationResponse eventReservationResponse = reservationService.bookReservation(1L);
        assertThat(eventReservationResponse).isNotNull();
        assertThat(eventReservationResponse.getEventStatus().name()).isEqualTo("UPCOMING");
        assertThat(eventReservationResponse.getReservationStatus().name()).isEqualTo("BOOKED");
    }

    @Test
    void testThatWhenMaxAccountReservationIsReached_ThrowsException(){
        Exception exception = assertThrows(ConflictException.class, ()-> reservationService.bookReservation(1L));
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualToIgnoringCase("No available bookings for event");

    }

    @Test
    void testBookReservation_WhenEventHasEnded(){
        assertThrows(AppException.class, ()-> reservationService.bookReservation(2L));
    }
}