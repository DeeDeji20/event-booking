package com.musala.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.musala.util.AppUtil.EMAIL_REGEX;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationRequest {
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotNull
    @Email(regexp = EMAIL_REGEX)
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;}
