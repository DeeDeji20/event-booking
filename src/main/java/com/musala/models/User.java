package com.musala.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.models.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
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
    @JsonIgnore
    private String password;
    @UpdateTimestamp
    private Timestamp createdAt;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Authority> authorities;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}
