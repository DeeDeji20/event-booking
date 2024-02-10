package com.musala.repository;

import com.musala.models.User;
import com.musala.models.enums.Authority;
import com.musala.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


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