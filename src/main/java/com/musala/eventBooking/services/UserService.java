package com.musala.eventBooking.services;

import com.musala.eventBooking.dtos.request.UserRegistrationDto;

public interface UserService {
    String createUser(UserRegistrationDto userRegistrationDto);
}
