package com.autentia.controller;


import com.autentia.domain.UserDto;
import com.autentia.interfaces.GroupService;
import com.autentia.interfaces.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;


import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    private GroupService groupService;


    public UserController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @Post("/users")
    public HttpResponse<UserDto> createUSer(@Body UserDto user){

        UserDto userCreated = userService.createUser(user);

        return HttpResponse.ok(userCreated);

    }

    @Get("/groups/{groupId}/users")
    public HttpResponse<List<UserDto>> getUsersByGroup(@PathVariable Long groupId) {

        List<UserDto> groupUsers = groupService.findUsersByGroup(groupId);

        return HttpResponse.ok(groupUsers);

    }

}
