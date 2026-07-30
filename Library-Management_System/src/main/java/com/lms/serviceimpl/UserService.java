package com.lms.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.UserDto;
import com.lms.entity.Address;
import com.lms.entity.Library;
import com.lms.entity.User;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.LibraryNotFoundException;
import com.lms.exception.UserNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.LibraryRepository;
import com.lms.repository.UserRepository;
import com.lms.service.IUserService;

@Service
public class UserService implements IUserService {

	final ModelMapper modelMapper;
	final UserRepository userRepository;
	final HttpHeaders httpHeaders;
	final AddressRepository addressRepository;
	final LibraryRepository libraryRepository;

	UserService(ModelMapper modelMapper, UserRepository userRepository, HttpHeaders httpHeaders,
			AddressRepository addressRepository,LibraryRepository libraryRepository) {
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.httpHeaders = httpHeaders;
		this.addressRepository = addressRepository;
		this.libraryRepository=libraryRepository;
	}

	@Override
	public ResponseEntity<UserDto> saveUser(UserDto userDto, int addressId,int libraryId) {

		User user = modelMapper.map(userDto, User.class);
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		Optional<Library>optionalLibrary =libraryRepository.findById(libraryId);
		if (optionalAddress.isPresent()&&optionalLibrary.isPresent() ) {
			Address address = optionalAddress.get();
			Library library =optionalLibrary.get();
			List<User>libraryUsers =library.getUsers();
			if(libraryUsers==null)
			{
				libraryUsers = new ArrayList<>();
				
			}
			libraryUsers.add(user);
			library.setUsers(libraryUsers);
			user.setAddress(address);
			user.setLibrary(library);
			userRepository.save(user);
			address.setUser(user);
			addressRepository.save(address);
			return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
		}

		else if(optionalAddress.isEmpty()){
			throw new AddressNotFoundException("Address Not Found");
		}
		else
		{
			throw new LibraryNotFoundException("Library not found ");
		}
	}

	@Override
	public ResponseEntity<User> findUser(int userId) {
		Optional<User> optinalUser = userRepository.findById(userId);

		if (optinalUser.isPresent()) {
			User user = optinalUser.get();
			
			return ResponseEntity.status(HttpStatus.OK).body(user);

		} else {
			
			throw new UserNotFoundException("User Not Found");
			
		}
	}

	@Override
	public ResponseEntity<List<UserDto>> findAllUser() {

		List<User> users = userRepository.findAll();

		List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();

		return ResponseEntity.status(HttpStatus.OK).body(userDtos);
	}

	@Override
	public ResponseEntity<String> deleteUser(int userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			Address address = user.getAddress();
			address.setUser(null);
			addressRepository.save(address);
			userRepository.delete(user);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Succssfully");
		}

		else
		{
		throw new UserNotFoundException("User Not Found");
		}

	}

}
