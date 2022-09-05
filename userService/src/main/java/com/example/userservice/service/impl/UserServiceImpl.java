package com.example.userservice.service.impl;

import com.example.userservice.Dto.UserDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.exception.UserException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);//create entity instance
        if(validateDuplicate(userEntity)){
            userRepository.save(userEntity);
        }else {
            throw new UserException("user info duplicate!");
        }

        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getUserList(String name) throws Exception{

        List<UserDto> userDtoList = new ArrayList<>();
        List<UserDto> userDtoList2 = new ArrayList<>();
        List<UserEntity> userEntityList = userRepository.findTop10ByName(name);
        ModelMapper mapper = new ModelMapper();

        //use function
        userDtoList = userEntityList.stream().map(p -> mapper.map(p, UserDto.class)).collect(Collectors.toList());
        //use consumer
        userEntityList.forEach(v -> {
            userDtoList2.add(mapper.map(v, UserDto.class));
        });
        return userDtoList;
    }

    @Override
    public UserDto getUser(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        return userDto;
    }

    private boolean validateDuplicate(UserEntity user){
        if(userRepository.findByEmail(user.getEmail()) != null
            || userRepository.findByUserId(user.getUserId()) != null)
            return false;
        else
            return true;
    }
}
