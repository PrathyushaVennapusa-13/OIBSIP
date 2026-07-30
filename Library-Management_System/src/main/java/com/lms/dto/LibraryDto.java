package com.lms.dto;


import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LibraryDto {
	
	private Integer libraryId;
	private String libraryName;
	@Min(6000000000L)
	@Max(9999999990L)
	private long phoneNumber;
	private List<BookDto> books;
	private List<UserDto>users;

	 
}
