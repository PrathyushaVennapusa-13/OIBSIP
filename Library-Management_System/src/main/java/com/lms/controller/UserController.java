package com.lms.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	final IUserService userService;
	
	UserController(IUserService userService)
	{
		this.userService =userService;
	}
	@PostMapping("/{addressId}/{libraryId}")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto, @PathVariable int addressId,@PathVariable int libraryId)
	{
		return userService.saveUser(userDto, addressId,libraryId);
	}
	
	@GetMapping("/{userId}")
	public  ResponseEntity<User> findUser(@PathVariable int userId)
	{
		return userService.findUser(userId);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> findAllUser()
	{
		return userService.findAllUser();
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) 
	{
		return userService.deleteUser(userId);
	}
}

