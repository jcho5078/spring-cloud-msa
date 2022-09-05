package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import com.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {

    private UserService userService;
    private Environment environment;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(Environment environment, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.environment = environment;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/health_check")
    public String status(HttpServletRequest request){
        return "user service working... " + request.getServerPort();
    }

    @PostMapping("/postRequest")
    public String postRequest(@RequestHeader("user-service-request") String header){
        return "user service working...";
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody RequestUser requestUser) throws Exception{
        requestUser.setPwd(bCryptPasswordEncoder.encode(requestUser.getPwd()));

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/users/users/{name}")
    public ResponseEntity<List<UserDto>> getUsers(@PathVariable String name) throws Exception {
        List<UserDto> userDtoList = userService.getUserList(name);

        //return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @PostMapping("/users/user/{name}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId) throws Exception{
        UserDto userDto = userService.getUser(userId);
        ModelMapper mapper = new ModelMapper();
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }
}
