package com.musala.eventBooking.services.impl;

import com.musala.eventBooking.dtos.request.UserRegistrationRequest;
import com.musala.eventBooking.exception.AppException;
import com.musala.eventBooking.models.User;
import com.musala.eventBooking.repositories.UserRepository;
import com.musala.eventBooking.security.models.Principal;
import com.musala.eventBooking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DevUserService implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String createUser(UserRegistrationRequest userRegistrationRequest) {
        User foundUser = findUserBy(userRegistrationRequest.getEmail());
        if (foundUser != null) throw new AppException(String.format("User with email %s already exists", foundUser.getEmail()));
        User user = User.builder()
                .name(userRegistrationRequest.getName())
                .email(userRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .build();
        userRepository.save(user);
        return "User Created Successfully";
    }

    private User findUserBy(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(
                String.format("user with email %s does not exist", email)
        ));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserBy(username);
        return new Principal(user);
    }
}
