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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

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



}