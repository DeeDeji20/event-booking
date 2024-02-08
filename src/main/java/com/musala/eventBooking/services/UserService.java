package com.musala.eventBooking.services;

import com.musala.eventBooking.dtos.request.EventReservationRequest;
import com.musala.eventBooking.dtos.request.UserRegistrationRequest;

public interface UserService {
    String createUser(UserRegistrationRequest userRegistrationRequest);
}
