package com.lms.dto;





import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookDto {

	
	private Integer bookId;
	@NotBlank
	@NotNull
	@Size(min=3)
	private String title;
	@NotBlank
	@NotNull
	@Size(min=3)
	private String author;
	@NotBlank
	@NotNull
	private String genre;
	
	private int noOfCopy;
    private int availableCopies;
    private int issuedCopies;
     

}
