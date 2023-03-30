package com.autentia.domain;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import java.math.BigDecimal;


@Introspected
public class UserExpenseSummary {

   private Long userId;
   private BigDecimal totalAmount;

   public UserExpenseSummary(Long userId, BigDecimal totalAmount) {
      this.userId = userId;
      this.totalAmount = totalAmount;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public BigDecimal getTotalAmount() {
      return totalAmount;
   }

   public void setTotalAmount(BigDecimal totalAmount) {
      this.totalAmount = totalAmount;
   }
}

