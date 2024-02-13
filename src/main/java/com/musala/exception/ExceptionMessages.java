package com.musala.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    EMAIL_SENT_SUCCESSFULLY("email sent to %s successfully"),
    EVENT_HAS_ENDED("Event already happened"),
    EVENT_NOT_FOUND("Event with id %d not found");
    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

}
