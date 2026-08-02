package com.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.Iservice.IAdminService;
import com.lms.dto.AdminDto;
import com.lms.dto.UserDto;
import com.lms.entity.Admin;
import com.lms.entity.User;
import com.lms.exception.AdminNotFoundException;
import com.lms.exception.InvalidPasswordException;
import com.lms.repository.AdminRepository;
import com.lms.repository.UserRepository;

@Service
public class AdminService implements IAdminService {
    
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminService(
            AdminRepository adminRepository,
            UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;

    }
    @Override
    public AdminDto adminLogin( String email, String password) {
        Admin admin =adminRepository.findByEmail(email) .orElseThrow(() -> new AdminNotFoundException(  "Admin Not Found"));
        if(!admin.getPassword() .equals(password)){
            throw new InvalidPasswordException("Invalid Password");
        }
        return convertToDto(admin);
    }
    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> list = new ArrayList<>();
        for(User user:users){
            list.add(convertUserToDto(user));
        }
        return list;

    }

    private AdminDto convertToDto( Admin admin){
        AdminDto dto = new AdminDto();
        dto.setAdminId(admin.getAdminId());
        dto.setAdminName(admin.getAdminName());
        dto.setEmail(admin.getEmail());
        dto.setPassword(admin.getPassword());
        return dto;

    }


    private UserDto convertUserToDto(User user){
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;

    }


}
