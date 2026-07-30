package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.exception.AddressNotFoundException;
import com.lms.exception.LibraryNotFoundException;
import com.lms.exception.UserNotFoundException;

public interface IUserService {

	public ResponseEntity<UserDto> saveUser(UserDto userDto ,int addressId,int libraryId) throws AddressNotFoundException,LibraryNotFoundException;
	public  ResponseEntity<User> findUser(int userId) throws UserNotFoundException;
	public ResponseEntity<List<UserDto>> findAllUser();
	public ResponseEntity<String> deleteUser(int userId)throws UserNotFoundException;
}
