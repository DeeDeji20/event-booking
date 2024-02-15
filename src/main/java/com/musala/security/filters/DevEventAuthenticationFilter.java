package com.musala.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.dtos.request.LoginRequest;
import com.musala.dtos.response.ApiResponse;
import com.musala.dtos.response.LoginResponse;
import com.musala.security.services.JwtService;
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

import static com.musala.util.AppUtil.AUTHENTICATION_FAILURE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@AllArgsConstructor
public class DevEventAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        LoginRequest loginRequest = extractAuthenticationCredentialsFrom(request);

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticationResult = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        return authenticationResult;
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        String token = jwtService.generateTokenFor(authResult.getPrincipal().toString());

        LoginResponse authenticationResponse = new LoginResponse();
        authenticationResponse.setAccessToken(token);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(authenticationResponse));
        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(failed.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(BAD_REQUEST.value());
        response.getOutputStream().write(mapper.writeValueAsBytes(apiResponse));
        response.flushBuffer();
    }

    private static LoginRequest extractAuthenticationCredentialsFrom(HttpServletRequest request) {
        try(InputStream inputStream = request.getInputStream()){
            byte[] requestBody =  inputStream.readAllBytes();
            return mapper.readValue(requestBody, LoginRequest.class);
        }catch (IOException exception){
            throw new BadCredentialsException(AUTHENTICATION_FAILURE);
        }
    }
}
