package com.lms.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.Iservice.IUserService;
import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.exception.InvalidPasswordException;
import com.lms.exception.UserAlreadyExistsException;
import com.lms.exception.UserNotFoundException;
import com.lms.repository.UserRepository;


@Service
public class UserService implements IUserService {


    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }







    // ===============================
    // REGISTER USER
    // ===============================

    @Override
    public UserDto registerUser(UserDto userDto) {


        if(userRepository
                .findByEmail(userDto.getEmail())
                .isPresent()){


            throw new UserAlreadyExistsException(
                    "Email already exists");

        }




        if(userRepository
                .findByPhoneNumber(
                        userDto.getPhoneNumber())
                .isPresent()){


            throw new UserAlreadyExistsException(
                    "Phone number already exists");

        }




        User user = new User();


        user.setUserName(
                userDto.getUserName());


        user.setEmail(
                userDto.getEmail());


        user.setPassword(
                userDto.getPassword());


        user.setPhoneNumber(
                userDto.getPhoneNumber());



        User saved =
                userRepository.save(user);



        return convertToDto(saved);

    }







    // ===============================
    // LOGIN USER
    // ===============================

    @Override
    public UserDto loginUser(
            String email,
            String password) {


        User user =
                userRepository.findByEmail(email)
                .orElseThrow(() ->
                new UserNotFoundException(
                        "Invalid Email"));



        if(!user.getPassword()
                .equals(password)){


            throw new InvalidPasswordException(
                    "Invalid Password");

        }



        return convertToDto(user);

    }








    // ===============================
    // GET USER BY ID
    // ===============================

    @Override
    public UserDto getUserById(Integer userId) {


        User user =
                userRepository.findById(userId)
                .orElseThrow(() ->
                new UserNotFoundException(
                        "User Not Found"));



        return convertToDto(user);

    }








    // ===============================
    // GET ALL USERS
    // ===============================

    @Override
    public List<UserDto> getAllUsers() {


        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();

    }








    // ===============================
    // UPDATE NAME
    // ===============================

    @Override
    public UserDto updateUserName(
            Integer userId,
            String userName) {


        User user =
                getUser(userId);



        user.setUserName(userName);



        return convertToDto(
                userRepository.save(user));

    }








    // ===============================
    // UPDATE EMAIL
    // ===============================

    @Override
    public UserDto updateUserEmail(
            Integer userId,
            String email) {


        User user =
                getUser(userId);



        if(userRepository
                .findByEmail(email)
                .isPresent()
                &&
                !user.getEmail().equals(email)){


            throw new UserAlreadyExistsException(
                    "Email already exists");

        }



        user.setEmail(email);



        return convertToDto(
                userRepository.save(user));

    }








    // ===============================
    // UPDATE PHONE
    // ===============================

    @Override
    public UserDto updateUserPhoneNumber(
            Integer userId,
            Long phoneNumber) {


        User user =
                getUser(userId);



        if(userRepository
                .findByPhoneNumber(phoneNumber)
                .isPresent()
                &&
                !user.getPhoneNumber()
                .equals(phoneNumber)){


            throw new UserAlreadyExistsException(
                    "Phone number already exists");

        }



        user.setPhoneNumber(phoneNumber);



        return convertToDto(
                userRepository.save(user));

    }








    // ===============================
    // UPDATE PASSWORD
    // ===============================

    @Override
    public UserDto updateUserPassword(
            Integer userId,
            String password) {


        User user =
                getUser(userId);



        user.setPassword(password);



        return convertToDto(
                userRepository.save(user));

    }








    private User getUser(Integer userId){


        return userRepository.findById(userId)
                .orElseThrow(() ->
                new UserNotFoundException(
                        "User Not Found"));

    }








    // ===============================
    // ENTITY TO DTO
    // ===============================

    private UserDto convertToDto(User user){


        UserDto dto =
                new UserDto();


        dto.setUserId(
                user.getUserId());


        dto.setUserName(
                user.getUserName());


        dto.setEmail(
                user.getEmail());


        dto.setPassword(
                user.getPassword());


        dto.setPhoneNumber(
                user.getPhoneNumber());



        return dto;

    }


}