package com.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lms.dto.LibraryDto;
import com.lms.service.ILibraryService;

@Controller
@RequestMapping("/library")
public class LibraryController {
	
	final ILibraryService libraryService;
	public LibraryController(ILibraryService libraryService)
	{
		this.libraryService =libraryService;
	}
	
	@PostMapping("{addressId}")
	public ResponseEntity<LibraryDto> saveLibrary(@RequestBody LibraryDto libraryDto,@PathVariable int addressId)
	{
		return libraryService.saveLibrary(libraryDto,addressId);
	}
	@GetMapping("/{libraryId}")
	public ResponseEntity<LibraryDto> getLibrarybyId(@PathVariable  int libraryId)
	{
		return libraryService.getLibrarybyId(libraryId);
	}

}
