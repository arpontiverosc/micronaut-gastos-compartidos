package com.autentia.mapper;


import com.autentia.domain.ExpenseDto;
import com.autentia.model.Expense;
import jakarta.inject.Singleton;


import java.time.OffsetDateTime;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ExpenseMapper {

    private UserMapper userMapper;

    public ExpenseMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<ExpenseDto> convertEntitiesToDto(List<Expense> expenses) {

        return expenses.stream()
                .map(expense -> {
                    return convertEntityToDto(expense);
                })
                .collect(Collectors.toList());

    }

    public Expense convertDtoToEntity(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setExpenseDate(OffsetDateTime.now());
        expense.setPrice(expenseDto.getPrice());
        expense.setCurrency(expenseDto.getCurrency());
        expense.setDescription(expenseDto.getDescription());
        return expense;
    }

    public ExpenseDto convertEntityToDto(Expense expense) {
        return ExpenseDto.builder()
                .expenseId(expense.getId())
                .user(userMapper.convertEntityToDto(expense.getUser()))
                .expenseDate(expense.getExpenseDate())
                .price(expense.getPrice())
                .currency(expense.getCurrency())
                .description(expense.getDescription())
                .build();
    }
}
