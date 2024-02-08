package com.musala.eventBooking.repositories;

import com.musala.eventBooking.models.User;
import com.musala.eventBooking.models.enums.Authority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        String email = "test@email.com";
        User user = new User();
        user.setEmail(email);
        user.setPassword("password");
        user.setAuthorities(Set.of(Authority.USER));
        userRepository.save(user);
        assertThat(userRepository.findByEmail(email)).isNotEmpty();
    }
}