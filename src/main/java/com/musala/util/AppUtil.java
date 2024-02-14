package com.musala.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class AppUtil {
    public static final String EMAIL = "email";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String NOTIFICATION_TEMPLATE = "notification.ftlh";
    public static final String NO_REPLY = "no-reply@email.com";
    public static final String USER_CREATED_SUCCESSFULLY = "User Created Successfully";
    public static final String RESERVED_SUCCESSFULLY = "Tickets reserved successfully";
    public static final String EMAIL_SENT_SUCCESSFULLY = "email sent to %s successfully";
    public static final String EMAIL_NOT_SENT_SUCCESSFULLY = "email not sent to %s";
    public static final String AUTHENTICATION_FAILURE = "Failed to extract authentication credentials";
    public static final String UNSUPPORTED_AUTHENTICATION_TYPE = "Unsupported authentication type";
    public static final String INCORRECT_CREDENTIALS = "Incorrect Authentication Credentials Supplied";
    public static final String AVAILABLE_RESERVATION_SLOT_MESSAGE = "System was only able to reserve %d slots for you as there were only that many slots left";

    public static Pageable paginateDataWith(Integer page, Integer size) {
        if (page < 1) page = ZERO.intValue();
        else page = page - ONE.intValue();
        if (size < 1) size = 15;
        return PageRequest.of(page, size, Sort.Direction.DESC, "id");
    }
}
