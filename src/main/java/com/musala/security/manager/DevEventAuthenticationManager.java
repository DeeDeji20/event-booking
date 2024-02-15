package com.musala.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static com.musala.util.AppUtil.UNSUPPORTED_AUTHENTICATION_TYPE;


@AllArgsConstructor
@Component
public class DevEventAuthenticationManager implements AuthenticationManager {
    private final AuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        boolean isAuthenticationSupported = authenticationProvider.supports(authentication.getClass());
        if (isAuthenticationSupported) return authenticationProvider.authenticate(authentication);
        throw new ProviderNotFoundException(UNSUPPORTED_AUTHENTICATION_TYPE);
    }
}
