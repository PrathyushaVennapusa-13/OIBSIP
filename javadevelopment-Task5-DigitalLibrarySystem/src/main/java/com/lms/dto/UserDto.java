package com.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Integer userId;
    private String userName;
    private String email;
    private String password;
    private Long phoneNumber;

}