package com.musala.eventBooking.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.eventBooking.dtos.request.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;

public class DevEventAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        LoginRequest loginRequest = extractAuthenticationCredentialsFrom(request);
        return null;
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

    }

    private static LoginRequest extractAuthenticationCredentialsFrom(HttpServletRequest request) {
        //TODO: remove hardcoded values
        ObjectMapper mapper = new ObjectMapper();
        try(InputStream inputStream = request.getInputStream()){
            byte[] requestBody =  inputStream.readAllBytes();
            return mapper.readValue(requestBody, LoginRequest.class);
        }catch (IOException exception){
            throw new BadCredentialsException("Failed to extract authentication credentials");
        }
    }
}
