package com.musala.controller;

import com.musala.dtos.request.UserRegistrationRequest;
import com.musala.services.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        String response = userService.createUser(userRegistrationRequest);
        return ResponseEntity.ok(response);
    }

}
