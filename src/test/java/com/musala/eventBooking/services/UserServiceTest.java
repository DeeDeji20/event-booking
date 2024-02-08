package com.musala.eventBooking.services;

import com.musala.eventBooking.dtos.request.UserRegistrationDto;
import com.musala.eventBooking.exception.AppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private UserRegistrationDto userRegistrationDto;

    @BeforeEach
    void setup(){
        userRegistrationDto = UserRegistrationDto.builder()
                .email("test@email.com")
                .name("John Doe")
                .password("password")
                .build();
    }

    @Test
    void createUser() {
        String response = userService.createUser(userRegistrationDto);
        assertThat(response).isEqualTo("User created successfully");
    }

    @Test
    void testThatUserAlreadyExists_ThrowsException(){
        assertThrows(AppException.class,()-> userService.createUser(userRegistrationDto));
    }

    @Test
    void testThatUserEmailIsValid(){
        assertThrows(AppException.class,()-> userService.createUser(userRegistrationDto));
    }
}