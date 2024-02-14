package com.musala.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;
}
