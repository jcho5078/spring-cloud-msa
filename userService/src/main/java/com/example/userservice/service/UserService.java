package com.example.userservice.service;

import com.example.userservice.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto) throws Exception;
    public List<UserDto> getUserList(String name) throws Exception;
    public UserDto getUser(String userId) throws Exception;
}
