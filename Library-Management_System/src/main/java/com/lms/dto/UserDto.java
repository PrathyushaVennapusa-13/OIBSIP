package com.lms.dto;

import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Integer UserId;
	@NotBlank
	@NotNull
	@Size(min=3)
	private String userName;
	@Min(6000000000L)
	@Max(9999999990L)
	private long phoneNumber;
	@NotBlank
	@NotNull
	@Size(min=8)
	private String email;

}

