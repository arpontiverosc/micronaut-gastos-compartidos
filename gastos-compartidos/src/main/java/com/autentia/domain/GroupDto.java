package com.autentia.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class GroupDto {

    private List<UserDto> users;
}
