package com.musala.dtos.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;


public class ReservationList extends PageImpl<ReservationResponse> implements Serializable {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ReservationList(
            @JsonProperty("content") List<ReservationResponse> content,
            @JsonProperty("number") int number,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") Long totalElements){
        super(content, PageRequest.of(number, size), totalElements);
    }

    public ReservationList(List<ReservationResponse> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ReservationList(List<ReservationResponse> content) {
        super(content);
    }
}
