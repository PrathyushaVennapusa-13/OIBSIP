package com.lms.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowDto {

	private int borrowId;

    private LocalDateTime issueDate;

    private LocalDateTime returnDate;
   
    
}
