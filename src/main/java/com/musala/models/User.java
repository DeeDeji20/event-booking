package com.musala.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.models.enums.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
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
