package com.autentia.service;


import com.autentia.domain.ExpenseDto;
import com.autentia.interfaces.BalanceService;
import com.autentia.interfaces.ExpenseService;
import com.autentia.interfaces.GroupService;
import com.autentia.interfaces.UserService;
import com.autentia.mapper.ExpenseMapper;
import com.autentia.model.Balance;
import com.autentia.model.Expense;
import com.autentia.repository.ExpenseRepository;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class ExpenseServiceImpl implements ExpenseService {


    private ExpenseRepository expenseRepository;


    private ExpenseMapper expenseMapper;


    private GroupService groupService;


    private BalanceService balanceService;


    private UserService userService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper, GroupService groupService, BalanceService balanceService, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.groupService = groupService;
        this.balanceService = balanceService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<ExpenseDto> getGroupExpensesByBalance(Long groupId, Long balanceId) {

        balanceService.findBalanceById(balanceId);
        groupService.findGroupById(groupId);

        List<Expense> expenses = expenseRepository.findByGroupIdAndBalanceIdOrderByExpenseDateDesc(groupId, balanceId);

        return expenseMapper.convertEntitiesToDto(expenses);
    }

    @Override
    @Transactional
    public ExpenseDto addExpenseToGroupBalance(Long groupId, Long balanceId, ExpenseDto expenseDto) {

        Expense expenseEntity = expenseMapper.convertDtoToEntity(expenseDto);

        expenseEntity.setGroup(groupService.findGroupById(groupId));

        Balance balanceEntity = balanceService.findBalanceById(balanceId);

        balanceService.checkBalanceOwnsGroup(balanceEntity, groupId);

        expenseEntity.setBalance(balanceEntity);

        expenseEntity.setUser(userService.findById(expenseDto.getUser().getUserId()));

        return expenseMapper.convertEntityToDto(expenseRepository.save(expenseEntity));

    }


}
