package com.musala.eventBooking.security.services;

import com.musala.eventBooking.security.models.Principal;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateTokenFor(String email);

    UserDetails extractUserDetailsFrom(String token);
}
