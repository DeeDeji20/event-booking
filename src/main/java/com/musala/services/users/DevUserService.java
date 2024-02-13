package com.musala.services.users;

import com.musala.dtos.request.UserRegistrationRequest;
import com.musala.dtos.response.ApiResponse;
import com.musala.models.User;
import com.musala.repositories.UserRepository;
import com.musala.security.models.Principal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.musala.exception.ExceptionMessages.USER_NOT_FOUND;
import static com.musala.models.enums.Authority.USER;
import static com.musala.util.AppUtil.USER_CREATED_SUCCESSFULLY;

@Service
@AllArgsConstructor
public class DevUserService implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ApiResponse<String> createUser(UserRegistrationRequest userRegistrationRequest) {
        User user = User.builder()
                .name(userRegistrationRequest.getName())
                .email(userRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .authorities(Set.of(USER))
                .build();
        userRepository.save(user);
        return new ApiResponse<>(USER_CREATED_SUCCESSFULLY);
    }

    @Override
    public User getUserByEmail(String email) {
        return findUserBy(email);
    }

    private User findUserBy(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(
                String.format(USER_NOT_FOUND.getMessage(), email)
        ));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserBy(username);
        return new Principal(user);
    }
}
