package com.musala.eventBooking.repositories;

import com.musala.eventBooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
