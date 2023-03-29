package com.autentia.model;

import com.autentia.client.Preference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private User user;
    private Preference preference;

}
