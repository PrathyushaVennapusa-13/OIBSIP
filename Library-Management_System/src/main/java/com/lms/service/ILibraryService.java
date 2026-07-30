package com.lms.service;

import org.springframework.http.ResponseEntity;

import com.lms.dto.LibraryDto;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.LibraryNotFoundException;

public interface ILibraryService {
	
	public ResponseEntity<LibraryDto> saveLibrary(LibraryDto libraryDto,int addressId) throws AddressNotFoundException;
	public ResponseEntity<LibraryDto> getLibrarybyId(int libraryId) throws LibraryNotFoundException;
}
