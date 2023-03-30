package com.autentia.domain;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class PaymentDto {

    private Long beneficiary;
    private Long debtor;
    private BigDecimal amountToPay;

}
