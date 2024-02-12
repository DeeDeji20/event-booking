package com.musala.services.users;

import com.musala.dtos.request.UserRegistrationRequest;
import com.musala.exception.AppException;
import com.musala.models.User;
import com.musala.repositories.UserRepository;
import com.musala.security.models.Principal;
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
        User user = User.builder()
                .name(userRegistrationRequest.getName())
                .email(userRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .build();
        userRepository.save(user);
        return "User Created Successfully";
    }

    @Override
    public User getUserByEmail(String email) {
        return findUserBy(email);
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
