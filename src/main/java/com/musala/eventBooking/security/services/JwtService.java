package com.musala.eventBooking.security.services;

import com.musala.eventBooking.security.models.Principal;

public interface JwtService {
    String generateTokenFor(String email);
}
