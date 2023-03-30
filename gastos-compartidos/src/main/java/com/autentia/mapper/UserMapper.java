package com.autentia.mapper;

import com.autentia.domain.UserDto;

import com.autentia.model.User;
import jakarta.inject.Singleton;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class UserMapper {


    private GroupMapper groupMapper;

    public UserMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public UserDto convertEntityToDto(User user) {
        return UserDto.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .groupsId(Objects.nonNull(user.getGroups())
                        ? user.getGroups().stream().map(group -> group.getId()).collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }


    public User convertDtoToEntity(UserDto userDto) {
        return new User(userDto.getUserName());
    }

    public List<UserDto> convertEntitiesToDto(List<User> groupUsers) {
        return groupUsers.stream().map(user -> convertEntityToDto(user)).collect(Collectors.toList());
    }
}
