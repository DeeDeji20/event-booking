package com.musala.dtos.response;

import com.musala.models.enums.Authority;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private Long id;
    private String name;
    private String email;
}
