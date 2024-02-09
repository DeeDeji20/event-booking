package com.musala.eventBooking.services.users;

import com.musala.eventBooking.dtos.request.UserRegistrationRequest;
import com.musala.eventBooking.models.User;

public interface UserService {
    String createUser(UserRegistrationRequest userRegistrationRequest);

    User getUserByEmail(String email);

}
