package com.autentia.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class TotalExpensePerUserDto {

    private Long userId;
    private BigDecimal totalAmount;

}
