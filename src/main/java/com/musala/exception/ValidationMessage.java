package com.musala.exception;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationMessage {
    private Date timeStamp;
    private int code;
    private String message;
}
