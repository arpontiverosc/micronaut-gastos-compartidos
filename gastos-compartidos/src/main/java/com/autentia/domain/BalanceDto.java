package com.autentia.domain;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class BalanceDto {

    private Long userId;
    private BigDecimal amountPaid;
    private BigDecimal amountFinal;


}
