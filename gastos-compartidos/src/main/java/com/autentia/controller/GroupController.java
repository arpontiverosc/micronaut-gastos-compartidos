package com.autentia.controller;


import com.autentia.domain.GroupUserDto;
import com.autentia.interfaces.GroupService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

@Controller
public class GroupController {


    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Post("/groups/{groupId}/users")
    public HttpResponse<Void> addUserToGroup(@PathVariable Long groupId, @Body GroupUserDto groupUser) {

        groupService.addUserToGroup(groupId, groupUser);

        return HttpResponse.ok();

    }



}
