package com.autentia.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class ExpenseDto {

    private Long expenseId;
    private UserDto user;
    private BigDecimal price;
    private String currency;
    private String description;
    private OffsetDateTime expenseDate;

}
