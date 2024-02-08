package com.musala;


import com.musala.eventBooking.models.User;
import com.musala.eventBooking.models.enums.Authority;
import com.musala.eventBooking.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return (args)->{
            User user = new User();
            user.setEmail("test@email.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setAuthorities(Set.of(Authority.USER));
            userRepository.save(user);
        };
    }
}