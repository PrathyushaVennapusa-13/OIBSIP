package com.lms.util;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Component
public class ApiError {
	
	private String msg;
	private List<String> details;
	private HttpStatus httpStatus;
	private LocalTime localtime;
	

}
