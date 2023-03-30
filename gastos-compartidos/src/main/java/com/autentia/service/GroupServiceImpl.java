package com.autentia.service;


import com.autentia.domain.GroupUserDto;
import com.autentia.domain.UserDto;
import com.autentia.exception.BadRequestException;
import com.autentia.exception.GroupNotFoundException;
import com.autentia.exception.UserNotGroupException;
import com.autentia.interfaces.GroupService;
import com.autentia.interfaces.UserService;
import com.autentia.mapper.GroupMapper;
import com.autentia.mapper.UserMapper;
import com.autentia.model.Group;
import com.autentia.model.User;
import com.autentia.repository.GroupRepository;
import com.autentia.repository.UserRepository;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class GroupServiceImpl implements GroupService {


    private UserService userService;

    private GroupRepository groupRepository;

    private UserRepository userRepository;


    private UserMapper userMapper;


    private GroupMapper groupMapper;

    public GroupServiceImpl(UserService userService, GroupRepository groupRepository, UserRepository userRepository, UserMapper userMapper, GroupMapper groupMapper) {
        this.userService = userService;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.groupMapper = groupMapper;
    }

    @Override
    @Transactional
    public Group findGroupById(Long groupId){
        if(Objects.isNull(groupId))
            throw new GroupNotFoundException();

        Optional<Group> group = groupRepository.findById(groupId);
        if(!group.isPresent()){
            throw new GroupNotFoundException();
        }
        return group.get();
    }


    @Override
    @Transactional
    public void addUserToGroup(Long groupId, GroupUserDto groupUser) {

        Group group = findGroupById(groupId);

        User user = userService.findById(groupUser.getUserId());

        group.getUsers().add(user);
        user.getGroups().add(group);

        userRepository.save(user);
        groupRepository.save(group);

    }

    @Override
    @Transactional
    public List<UserDto> findUsersByGroup(Long groupId) {
        if(Objects.isNull(groupId))
            throw new BadRequestException();

        Optional<Group> group = groupRepository.findById(groupId);

        if(!group.isPresent()){
                throw new GroupNotFoundException();
        }

        return userMapper.convertEntitiesToDto(group.get().getUsers());

    }

    @Override
    @Transactional
    public void checkUserIsPartOfTheGroup(Long groupId, Long userId) {

        Optional<UserDto> user = findUsersByGroup(groupId)
                .stream().filter(u -> u.getUserId().equals(userId))
                .findFirst();

        if (!user.isPresent()) {
            throw new UserNotGroupException();
        }
    }


}
