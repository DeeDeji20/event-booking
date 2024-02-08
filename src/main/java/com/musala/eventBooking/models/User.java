package com.musala.eventBooking.models;

import com.musala.eventBooking.models.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @UpdateTimestamp
    private Timestamp createdAt;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Authority> authorities;


}
