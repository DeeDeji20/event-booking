package com.musala.eventBooking.dtos.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;}
