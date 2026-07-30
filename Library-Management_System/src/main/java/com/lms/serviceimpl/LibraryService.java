package com.lms.serviceimpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.LibraryDto;
import com.lms.entity.Address;
import com.lms.entity.Library;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.LibraryNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.LibraryRepository;
import com.lms.service.ILibraryService;

@Service
public class LibraryService implements ILibraryService{
	
	final ModelMapper modelMapper;
	final LibraryRepository libraryRepository;
	final AddressRepository addressRepository;
	
	public  LibraryService(ModelMapper modelMapper,LibraryRepository libraryRepository, AddressRepository addressRepository)
	{
		this.modelMapper=modelMapper;
		this.libraryRepository=libraryRepository;
		this.addressRepository=addressRepository;
	}

	public ResponseEntity<LibraryDto> saveLibrary(LibraryDto libraryDto,int addressId)
	{
		Optional<Address>optionalAddress =addressRepository.findById(addressId);
		if(optionalAddress.isPresent())
		{
		Library library = modelMapper.map(libraryDto, Library.class);
		Address address =optionalAddress.get();
		library.setAddress(address);
		libraryRepository.save(library);
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryDto);
		}
		else
		{
			throw new  AddressNotFoundException("Address ID Not Found");
		}
	}
	public ResponseEntity<LibraryDto> getLibrarybyId(int libraryId)
	{
		Optional<Library> optionalLibrary =  libraryRepository.findById(libraryId);
		if(optionalLibrary.isPresent())
		{
			Library library =optionalLibrary.get();
			LibraryDto libraryDto =modelMapper.map(library, LibraryDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(libraryDto);
			
		}
		else
		{
			throw new LibraryNotFoundException("Library not found ");
		}
		
	}
	
}
