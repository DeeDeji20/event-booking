package com.musala.repositories;

import com.musala.models.Event;
import com.musala.models.Reservation;
import com.musala.models.User;
import com.musala.models.enums.ReservationStatus;
import com.musala.services.events.EventService;
import com.musala.services.users.UserService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.musala.models.enums.ReservationStatus.BOOKED;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @Test
    @Sql(scripts = {"/db/insert.sql"})
    public void testFindReservationByEvent() {
        ModelMapper mapper = new ModelMapper();
        Event event = mapper.map(eventService.getEventBy(1L), Event.class);
        List<Reservation> reservations = reservationRepository.findReservationByEventAndReservationStatus(event, BOOKED);
        System.out.println(reservations);
        assertThat(reservations).isNotEmpty();
    }

    @Test
    @Sql(scripts = {"/db/insert.sql"})
    public void testFindReservation(){
        User user = userService.getUserByEmail("deolaoladeji@gmail.com");
        Page<Reservation> reservations = reservationRepository.findReservationByUserV2(user, PageRequest.of(1, 25));
        System.out.println(reservations.getContent());
    }
}