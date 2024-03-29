package com.autentia.interfaces;


import com.autentia.domain.GroupUserDto;
import com.autentia.domain.UserDto;
import com.autentia.model.Group;

import javax.transaction.Transactional;
import java.util.List;

public interface GroupService {
    Group findGroupById(Long groupId);

    void addUserToGroup(Long groupId, GroupUserDto groupUser);


    List<UserDto> findUsersByGroup(Long groupId);

    @Transactional
    void checkUserIsPartOfTheGroup(Long groupId, Long userId);
}
