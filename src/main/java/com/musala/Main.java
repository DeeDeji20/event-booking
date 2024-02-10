package com.musala;


import com.musala.models.Event;
import com.musala.models.User;
import com.musala.models.enums.Authority;
import com.musala.models.enums.Category;
import com.musala.models.enums.EventStatus;
import com.musala.repositories.EventRepository;
import com.musala.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@SpringBootApplication
@EnableCaching
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, EventRepository eventRepository, PasswordEncoder passwordEncoder){
        return (args)->{
            User user = new User();
            user.setName("John Doe");
            user.setEmail("test@email.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setAuthorities(Set.of(Authority.USER));
            user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            userRepository.save(user);

            Event event1 = Event.builder().eventStatus(EventStatus.UPCOMING).category(Category.GAME).name("Game time").description("Time out for games").eventDate(LocalDateTime.of(LocalDate.of(2024, 2, 15), LocalTime.of(10, 0, 0))).maxAttendeesCount(100).availableAttendeesCount(0).build();
            Event event2 = Event.builder().eventStatus(EventStatus.ENDED).category(Category.CONFERENCE).name("Conference time").description("Meeting").eventDate(LocalDateTime.of(LocalDate.of(2024, 2, 6), LocalTime.of(9, 0, 0))).maxAttendeesCount(100).availableAttendeesCount(0).build();
            Event event3 = Event.builder().eventStatus(EventStatus.ACTIVE).category(Category.CONCERT).name("Fixed outing").description("Meeting").eventDate(LocalDateTime.of(LocalDate.of(2024, 2, 20), LocalTime.of(9, 0, 0))).maxAttendeesCount(100).availableAttendeesCount(0).build();

            eventRepository.save(event1);
            eventRepository.save(event2);
            eventRepository.save(event3);
        };
    }
}