package com.musala.eventBooking.security.config;


import com.musala.eventBooking.models.enums.Authority;
import com.musala.eventBooking.security.filters.DevEventAuthenticationFilter;
import com.musala.eventBooking.security.filters.DevEventAuthorizationFilter;
import com.musala.eventBooking.security.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final DevEventAuthorizationFilter authorizationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        DevEventAuthenticationFilter authenticationFilter = new DevEventAuthenticationFilter(authenticationManager, jwtService);
        authenticationFilter.setFilterProcessesUrl("/auth/login");
        return http.csrf(AbstractHttpConfigurer::disable)
                   .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   .addFilterAt(authenticationFilter, BasicAuthenticationFilter.class)
                   .addFilterBefore(authorizationFilter, DevEventAuthenticationFilter.class)
                   .authorizeHttpRequests(c->c.requestMatchers("/auth/login").permitAll()
                           .requestMatchers(HttpMethod.GET, "/api/reservation").permitAll()
                   )
                   .authorizeHttpRequests(c->c.anyRequest().authenticated())
                   .build();
    }


}
