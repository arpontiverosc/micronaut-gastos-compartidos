package com.autentia.controller;



import com.autentia.domain.ExpenseDto;
import com.autentia.interfaces.ExpenseService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;


import java.util.List;

@Controller
public class ExpenseController {

    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Get("/groups/{groupId}/balances/{balanceId}/expenses")
    public HttpResponse<List<ExpenseDto>> getGroupExpensesByBalance(@PathVariable Long groupId, @PathVariable Long balanceId) {

        List<ExpenseDto> expensesResult = expenseService.getGroupExpensesByBalance(groupId, balanceId);

        return HttpResponse.ok(expensesResult);

    }

    @Post("/groups/{groupId}/balances/{balanceId}/expenses")
    public HttpResponse<ExpenseDto> addExpenseToGroupBalance(@PathVariable Long groupId,
                                                                @PathVariable Long balanceId,
                                                                @Body ExpenseDto expenseDto) {

        ExpenseDto expensesResult = expenseService.addExpenseToGroupBalance(groupId, balanceId, expenseDto);

        return HttpResponse.ok(expensesResult);

    }


}
