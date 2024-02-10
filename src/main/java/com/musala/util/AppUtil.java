package com.musala.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class AppUtil {
    public static final String NOT_FOUND = "Data not found";

    public static PageRequest createPageRequestWith(Integer page, Integer size) {
        if (page < 1) page = ZERO.intValue();
        else page = page - ONE.intValue();
        if (size < 1) size = 15;
        return PageRequest.of(page, size, Sort.Direction.DESC, "id");
    }
}
