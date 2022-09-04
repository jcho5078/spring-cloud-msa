package com.example.userservice.service;

import com.example.userservice.Dto.UserDto;
import com.example.userservice.requestObj.RequestUser;
import org.springframework.stereotype.Service;

public interface UserService {

    public UserDto createUser(UserDto userDto);
}
