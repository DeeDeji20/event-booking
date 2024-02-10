package com.musala.security.filters;

import com.musala.security.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

import static com.musala.security.SecurityUtils.getAuthenticationWhiteList;


@Component
@AllArgsConstructor
public class DevEventAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Collection<String> authenticationWhiteList = getAuthenticationWhiteList();
        boolean isEndpointPublic = authenticationWhiteList.contains(request.getServletPath());
        if (isEndpointPublic) {
            filterChain.doFilter(request, response);
        }else {
            String authorizationHeader = request.getHeader("AUTHORIZATION");
            String token = extractTokenFrom(authorizationHeader);
            UserDetails userDetails = jwtService.extractUserDetailsFrom(token);
            authorize(userDetails);
            filterChain.doFilter(request, response);
        }
    }

    private static void authorize(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static String extractTokenFrom(String authorizationHeader) {
        boolean isAuthorizationHeaderPresent = authorizationHeader != null;
        String token = null;
        if (isAuthorizationHeaderPresent) token = authorizationHeader.substring("Bearer ".length());
        return token;
    }
}
