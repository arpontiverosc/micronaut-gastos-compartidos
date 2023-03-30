package com.autentia.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class UserDto {

    private Long userId;
    private String userName;
    private List<Long> groupsId;


}
