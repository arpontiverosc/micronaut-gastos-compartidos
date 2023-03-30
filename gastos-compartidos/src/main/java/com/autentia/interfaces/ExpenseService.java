package com.autentia.interfaces;



import com.autentia.domain.ExpenseDto;

import java.util.List;

public interface ExpenseService {
    List<ExpenseDto> getGroupExpensesByBalance(Long groupId, Long balanceId);

    ExpenseDto addExpenseToGroupBalance(Long groupId, Long balanceId, ExpenseDto expenseDto);

}
