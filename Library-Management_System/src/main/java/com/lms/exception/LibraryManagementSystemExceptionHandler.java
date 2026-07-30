package com.lms.exception;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lms.util.ApiError;

@ControllerAdvice
public class LibraryManagementSystemExceptionHandler {
	
	ApiError error;
	LibraryManagementSystemExceptionHandler(ApiError error)
	{
		this.error =error;
	}
	@ExceptionHandler
	public ResponseEntity<Object> handleAddressNotfoundException(AddressNotFoundException ex)
	{
		error.setMsg(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Address Not Found");
		error.setDetails(details);
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		error.setLocaltime(LocalTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException uEx)
	{
		error.setMsg(uEx.getMessage());
		List<String> details = new ArrayList<>();
		details.add("User Not Found");
		error.setDetails(details);
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		error.setLocaltime(LocalTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler
	public ResponseEntity<Object> handleLibraryNotFoundException(LibraryNotFoundException uEx)
	{
		error.setMsg(uEx.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Library Not Found");
		error.setDetails(details);
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		error.setLocaltime(LocalTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler
	public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException uEx)
	{
		error.setMsg(uEx.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Book Not Found");
		error.setDetails(details);
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		error.setLocaltime(LocalTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler
	public ResponseEntity<Object> handleBorrowNotFoundException(BorrowNotFoundException uEx)
	{
		error.setMsg(uEx.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Borrow Not Found");
		error.setDetails(details);
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		error.setLocaltime(LocalTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
