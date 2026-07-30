package com.lms.dto;





import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private Integer addressId;
	@Size(min=3)
	private String houseNumber;
	@NotBlank
	@NotNull
	private String area;
	@NotBlank
	@NotNull
	private String city;
	@NotBlank
	@NotNull
	private String state;
	@NotBlank
	@NotNull
	private String country;
	@Digits(integer =6,fraction=0)
	private int pincode;
}
