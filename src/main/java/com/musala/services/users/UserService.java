package com.musala.services.users;

import com.musala.dtos.request.UserRegistrationRequest;
import com.musala.models.User;

public interface UserService {
    String createUser(UserRegistrationRequest userRegistrationRequest);

    User getUserByEmail(String email);

}
