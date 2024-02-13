package com.musala.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.musala.util.AppUtil.EMAIL;
import static java.time.Instant.now;


@Service
@AllArgsConstructor
public class DevEventJwtService implements JwtService{
    private final UserDetailsService userDetailsService;

//    @Value("secret.key")
//    private static String SECRET;
    @Override
    public String generateTokenFor(String  email) {
        //TODO: remove hardcoded values
        return JWT.create()
                  .withClaim(EMAIL, email)
                  .withExpiresAt(now().plusSeconds(86400))
                  .withIssuedAt(now())
                  .sign(HMAC512("secret"));
    }

    @Override
    public UserDetails extractUserDetailsFrom(String token) {
        JWTVerifier verifier = JWT.require(HMAC512("secret"))
                .withClaimPresence(EMAIL)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        Claim claim = decodedJWT.getClaim(EMAIL);
        String email = claim.asString();
        return userDetailsService.loadUserByUsername(email);
    }
}
