package com.musala;


import com.musala.eventBooking.models.Event;
import com.musala.eventBooking.models.User;
import com.musala.eventBooking.models.enums.Authority;
import com.musala.eventBooking.models.enums.Category;
import com.musala.eventBooking.models.enums.EventStatus;
import com.musala.eventBooking.repositories.EventRepository;
import com.musala.eventBooking.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, EventRepository eventRepository, PasswordEncoder passwordEncoder){
        return (args)->{
            User user = new User();
            user.setEmail("test@email.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setAuthorities(Set.of(Authority.USER));
            userRepository.save(user);

            Event event1 = Event.builder().eventStatus(EventStatus.UPCOMING).category(Category.GAME).name("Game time").description("Time out for games").eventDate(LocalDateTime.of(LocalDate.of(2024, 1, 15), LocalTime.of(10, 0, 0))).build();
            Event event2 = Event.builder().eventStatus(EventStatus.ENDED).category(Category.CONFERENCE).name("Conference time").description("Meeting").eventDate(LocalDateTime.of(LocalDate.of(2024, 1, 6), LocalTime.of(9, 0, 0))).build();

            eventRepository.save(event1);
            eventRepository.save(event2);
        };
    }
}