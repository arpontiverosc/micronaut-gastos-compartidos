package com.autentia.repository;

import com.autentia.model.Expense;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByGroupIdAndBalanceIdOrderByExpenseDateDesc(Long groupId, Long balanceId);

    @Query(value = "SELECT e.user.id, SUM(e.price) AS TOTAL_AMOUNT FROM expenses e " +
            "WHERE e.group.id = :groupId AND e.balance.id = :balanceId " +
            "GROUP BY e.user.id",
             nativeQuery = true)
    List<Object[]> findTotalAmountByUser(Long groupId, Long balanceId);

}
