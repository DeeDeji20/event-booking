package com.musala;


import com.musala.models.Event;
import com.musala.models.Reservation;
import com.musala.models.User;
import com.musala.models.enums.Authority;
import com.musala.models.enums.Category;
import com.musala.models.enums.EventStatus;
import com.musala.models.enums.ReservationStatus;
import com.musala.repositories.EventRepository;
import com.musala.repositories.ReservationRepository;
import com.musala.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(UserRepository userRepository, EventRepository eventRepository, ReservationRepository reservationRepository, PasswordEncoder passwordEncoder){
//        return (args)->{
//            User user = new User();
//            user.setName("John Doe");
//            user.setEmail("deolaoladeji@gmail.com");
//            user.setPassword(passwordEncoder.encode("password"));
//            user.setAuthorities(Set.of(Authority.USER));
//            user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
//            userRepository.save(user);
//
//            Event event1 = Event.builder().eventStatus(EventStatus.UPCOMING).category(Category.GAME).name("Game time").description("Time out for games").eventDate(LocalDateTime.of(LocalDate.of(2024, 2, 12), LocalTime.of(10, 0, 0))).maxAttendeesCount(100).availableAttendeesCount(0).build();
//            Event event2 = Event.builder().eventStatus(EventStatus.ENDED).category(Category.CONFERENCE).name("Conference time").description("Meeting").eventDate(LocalDateTime.of(LocalDate.of(2024, 2, 6), LocalTime.of(9, 0, 0))).maxAttendeesCount(100).availableAttendeesCount(0).build();
//            Event event3 = Event.builder().eventStatus(EventStatus.ACTIVE).category(Category.CONCERT).name("Fixed outing").description("Meeting").eventDate(LocalDateTime.of(LocalDate.of(2024, 2, 20), LocalTime.of(9, 0, 0))).maxAttendeesCount(100).availableAttendeesCount(0).build();
//
//            eventRepository.save(event1);
//            eventRepository.save(event2);
//            eventRepository.save(event3);
//
//            Reservation reservation1 = Reservation.builder().reservationStatus(ReservationStatus.BOOKED).ticketCount(1).build();
//            Reservation reservation2 = Reservation.builder().reservationStatus(ReservationStatus.BOOKED).ticketCount(3).build();
//            Reservation reservation3 = Reservation.builder().reservationStatus(ReservationStatus.CANCELED).ticketCount(4).build();
//
//            reservationRepository.save(reservation1);
//            reservationRepository.save(reservation2);
//            reservationRepository.save(reservation3);
//        };
//    }
}