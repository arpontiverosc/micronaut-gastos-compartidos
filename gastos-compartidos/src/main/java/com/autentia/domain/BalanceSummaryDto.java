package com.autentia.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class BalanceSummaryDto {

    private int users;
    private BigDecimal totalAmount;
    private BigDecimal amountPerUser;
    private List<BalanceDto> balances;
    private List<PaymentDto> payments;
}
