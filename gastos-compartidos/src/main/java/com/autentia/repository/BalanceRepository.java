package com.autentia.repository;

import com.autentia.model.Balance;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;


@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
