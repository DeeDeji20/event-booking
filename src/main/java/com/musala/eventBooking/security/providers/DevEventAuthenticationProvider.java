package com.musala.eventBooking.security.providers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DevEventAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        boolean isPasswordValid = passwordEncoder.matches(password, userDetails.getPassword());
        if (isPasswordValid){
            Authentication authenticationResult =
                    new UsernamePasswordAuthenticationToken(null, null, userDetails.getAuthorities());
            return authenticationResult;
        }
        //TODO: remove hardcoded values
        throw new BadCredentialsException("Incorrect Authentication Credentials Supplied");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
