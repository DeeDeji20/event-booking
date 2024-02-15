package com.musala.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.models.enums.Authority;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse implements Serializable {
    private Long id;
    private String name;
    private String email;
}
