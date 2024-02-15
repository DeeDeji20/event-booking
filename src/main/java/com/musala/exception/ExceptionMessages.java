package com.musala.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    EMAIL_NOT_SENT_SUCCESSFULLY("email sent to %s successfully"),
    USER_NOT_FOUND("user with email %s does not exist"),
    EVENT_HAS_ENDED("Event already happened"),
    EVENT_NOT_FOUND("Event with id %d not found"),
    RESERVATION_NOT_FOUND("Reservation with id %d not found");
    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

}
