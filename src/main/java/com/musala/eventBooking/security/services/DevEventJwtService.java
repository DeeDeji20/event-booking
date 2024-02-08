package com.musala.eventBooking.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.musala.eventBooking.security.models.Principal;
import org.springframework.stereotype.Service;


@Service
public class DevEventJwtService implements JwtService{
    @Override
    public String generateTokenFor(String  email) {
        return JWT.create().withClaim("email", email).sign(Algorithm.HMAC512("secret"));
    }
}
