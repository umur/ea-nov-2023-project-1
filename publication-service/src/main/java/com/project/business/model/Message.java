package com.project.business.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class Message {
    private String message;
    private LocalDate date;

}
