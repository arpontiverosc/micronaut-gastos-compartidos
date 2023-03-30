package com.autentia.repository;

import com.autentia.domain.UserExpenseSummary;
import com.autentia.model.Expense;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.http.annotation.QueryValue;

import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByGroupIdAndBalanceIdOrderByExpenseDateDesc(Long groupId, Long balanceId);

    @Query(value = "SELECT e.user.id as userId, SUM(e.price) as totalAmount " +
            "FROM expenses e WHERE e.group.id = :groupId AND e.balance.id = :balanceId " +
            "GROUP BY e.user.id")
    List<UserExpenseSummary> findTotalAmountByUser(@QueryValue("groupId") Long groupId, @QueryValue("balanceId") Long balanceId);




}
