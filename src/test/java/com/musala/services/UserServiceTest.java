package com.musala.services;

import com.musala.dtos.request.UserRegistrationRequest;
import com.musala.dtos.response.ApiResponse;
import com.musala.exception.AppException;
import com.musala.services.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private UserRegistrationRequest userRegistrationRequest;

    @BeforeEach
    void setup(){
        userRegistrationRequest = UserRegistrationRequest.builder()
                .email("test@email.com")
                .name("John Doe")
                .password("password")
                .build();
    }

    @Test
    void createUser() {
        ApiResponse<String> response = userService.createUser(userRegistrationRequest);
        assertThat(response.getData()).isEqualToIgnoringCase("User created successfully");
    }

}