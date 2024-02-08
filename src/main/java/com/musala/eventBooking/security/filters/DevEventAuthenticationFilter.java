package com.musala.eventBooking.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.eventBooking.dtos.request.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;


@AllArgsConstructor
public class DevEventAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        LoginRequest loginRequest = extractAuthenticationCredentialsFrom(request);

         //1. Create an Authentication object that is not yet authenticated
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
         // 2. Use the AuthenticationManager to authenticate the unAuthenticated Authentication object
        Authentication authenticationResult = authenticationManager.authenticate(authentication);
        //3. Put the now authenticated Authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        return authenticationResult;
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
