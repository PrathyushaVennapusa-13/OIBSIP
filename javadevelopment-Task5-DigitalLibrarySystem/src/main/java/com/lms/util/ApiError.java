package com.lms.util;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

    private String message;
    private int statusCode;
    private LocalDateTime timeStamp;

}
