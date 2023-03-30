package com.autentia.service;

import com.autentia.domain.UserDto;

import com.autentia.exception.UserNotFoundException;
import com.autentia.interfaces.UserService;
import com.autentia.mapper.UserMapper;
import com.autentia.model.User;
import com.autentia.repository.UserRepository;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto user) {

        return userMapper.convertEntityToDto(userRepository.save(userMapper.convertDtoToEntity(user)));

    }

    @Override
    @Transactional
    public User findById(Long userId) {
        if(Objects.isNull(userId))
            throw new UserNotFoundException();

        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException();
        }
        return userOptional.get();

    }

}
