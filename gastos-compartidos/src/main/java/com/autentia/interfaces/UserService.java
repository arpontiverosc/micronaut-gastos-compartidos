package com.autentia.interfaces;

import com.autentia.domain.UserDto;
import com.autentia.model.User;


import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);


    User findById(Long userId);

}
