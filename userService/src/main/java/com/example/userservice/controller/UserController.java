package com.example.userservice.controller;

import com.example.userservice.Dto.UserDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.requestObj.RequestUser;
import com.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user-service")
public class UserController {

    private UserService userService;
    private Environment environment;

    @Autowired
    public UserController(Environment environment, UserService userService){
        this.environment = environment;
        this.userService = userService;
    }

    @PostMapping("/health_check")
    public String status(){
        return "user service working...";
    }

    @PostMapping("/postRequest")
    public String postRequest(@RequestHeader("user-service-request") String header){
        return "user service working...";
    }

    @PostMapping("/createUser")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody RequestUser requestUser){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }
}
