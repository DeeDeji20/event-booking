package com.musala.dtos.request;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EventSearchRequest {
    private  String name;
    private String category;
    private String startDate;
    private String endDate;
    private Integer page;
    private Integer size;
}
