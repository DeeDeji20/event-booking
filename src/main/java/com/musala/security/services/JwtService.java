package com.musala.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateTokenFor(String email);

    UserDetails extractUserDetailsFrom(String token);
}
