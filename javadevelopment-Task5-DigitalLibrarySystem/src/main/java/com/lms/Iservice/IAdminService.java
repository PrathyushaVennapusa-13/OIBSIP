package com.lms.Iservice;

import java.util.List;

import com.lms.dto.AdminDto;
import com.lms.dto.UserDto;

public interface IAdminService {
	 AdminDto adminLogin(String email, String password);
	 List<UserDto> getAllUsers();
}
