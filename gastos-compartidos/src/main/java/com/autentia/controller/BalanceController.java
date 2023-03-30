package com.autentia.controller;


import com.autentia.domain.BalanceSummaryDto;
import com.autentia.interfaces.BalanceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

@Controller
public class BalanceController {

    private BalanceService balanceService;


    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Get("/groups/{groupId}/balances/{balanceId}")
    public HttpResponse<BalanceSummaryDto> getBalanceByGroup(@PathVariable Long groupId, @PathVariable Long balanceId) {

        BalanceSummaryDto balanceSummary = balanceService.getBalancesByGroup(groupId, balanceId);

        return HttpResponse.ok(balanceSummary);

    }


}
