package com.musala.security;

import java.util.Collection;
import java.util.Set;

public class SecurityUtils {


    public static Collection<String> getAuthenticationWhiteList(){
        return Set.of(
                "/auth/login",
                "/users",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v3/api-docs.yaml",
                "/h2-console/**"
        );
    }
}
