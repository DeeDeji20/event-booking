package com.musala.eventBooking.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.musala.eventBooking.security.models.Principal;
import com.musala.eventBooking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static java.time.Instant.now;


@Service
@AllArgsConstructor
public class DevEventJwtService implements JwtService{
    private final UserDetailsService userDetailsService;
    @Override
    public String generateTokenFor(String  email) {
        //TODO: remove hardcoded values
        return JWT.create()
                  .withClaim("email", email)
                  .withExpiresAt(now().plusSeconds(86400))
                  .withIssuedAt(now())
                  .sign(HMAC512("secret"));
    }

    @Override
    public UserDetails extractUserDetailsFrom(String token) {
        JWTVerifier verifier = JWT.require(HMAC512("secret"))
                .withClaimPresence("email")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        Claim claim = decodedJWT.getClaim("email");
        String email = claim.asString();
        return userDetailsService.loadUserByUsername(email);
    }
}
