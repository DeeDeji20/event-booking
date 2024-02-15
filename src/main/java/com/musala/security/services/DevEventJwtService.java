package com.musala.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.musala.util.AppUtil.EMAIL;
import static java.lang.Long.parseLong;
import static java.time.Instant.now;


@Configuration
@RequiredArgsConstructor
public class DevEventJwtService implements JwtService{
    private final UserDetailsService userDetailsService;

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    @Value("${jwt.token.validity}")
    private String jwtTokenValidity;
    @Override
    public String generateTokenFor(String  email) {
        return JWT.create()
                  .withClaim(EMAIL, email)
                  .withExpiresAt(now().plusSeconds(parseLong(jwtTokenValidity)))
                  .withIssuedAt(now())
                  .sign(HMAC512(jwtSigningKey));
    }

    @Override
    public UserDetails extractUserDetailsFrom(String token) {
        JWTVerifier verifier = JWT.require(HMAC512(jwtSigningKey))
                .withClaimPresence(EMAIL)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        Claim claim = decodedJWT.getClaim(EMAIL);
        String email = claim.asString();
        return userDetailsService.loadUserByUsername(email);
    }
}
