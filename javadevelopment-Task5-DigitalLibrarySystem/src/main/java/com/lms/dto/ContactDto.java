package com.lms.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {


    private Integer contactId;

    private String subject;

    private String message;

    private LocalDate createdDate;


    private Integer userId;

    private String userName;

}