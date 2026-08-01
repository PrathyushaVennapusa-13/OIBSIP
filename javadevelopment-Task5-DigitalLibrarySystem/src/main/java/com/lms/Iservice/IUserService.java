package com.lms.Iservice;

import java.util.List;

import com.lms.dto.UserDto;

public interface IUserService {

	UserDto registerUser(UserDto userDto);

    UserDto loginUser(String email, String password);

    UserDto getUserById(Integer userId);

    UserDto updateUserName(Integer userId, String userName);

    UserDto updateUserEmail(Integer userId, String email);

    UserDto updateUserPhoneNumber(Integer userId, Long phoneNumber);

    UserDto updateUserPassword(Integer userId, String password);

    public List<UserDto> getAllUsers();
}
