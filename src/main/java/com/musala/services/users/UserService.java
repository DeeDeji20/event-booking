package com.musala.services.users;

import com.musala.dtos.request.UserRegistrationRequest;
import com.musala.dtos.response.ApiResponse;
import com.musala.models.User;

public interface UserService {
    ApiResponse<String> createUser(UserRegistrationRequest userRegistrationRequest);

    User getUserByEmail(String email);

}
