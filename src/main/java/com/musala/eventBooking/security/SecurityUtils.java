package com.musala.eventBooking.security;

import java.util.Collection;
import java.util.Set;

public class SecurityUtils {


    public static Collection<String> getAuthenticationWhiteList(){
        return Set.of(
                "/auth/login",
                "/register",
                "/api/reservation"
        );
    }
}
