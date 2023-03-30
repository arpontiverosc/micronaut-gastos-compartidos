package com.autentia.service;



import com.autentia.domain.*;
import com.autentia.exception.BadRequestException;
import com.autentia.exception.BalanceNotFoundException;
import com.autentia.exception.BalanceNotInformedException;
import com.autentia.interfaces.BalanceService;
import com.autentia.interfaces.GroupService;
import com.autentia.mapper.UserMapper;
import com.autentia.model.Balance;
import com.autentia.model.User;
import com.autentia.repository.BalanceRepository;
import com.autentia.repository.ExpenseRepository;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class BalanceServiceImpl implements BalanceService {


    private BalanceRepository balanceRepository;

    private ExpenseRepository expenseRepository;


    private GroupService groupService;


    private UserMapper userMapper;

    public BalanceServiceImpl(BalanceRepository balanceRepository, ExpenseRepository expenseRepository, GroupService groupService, UserMapper userMapper) {
        this.balanceRepository = balanceRepository;
        this.expenseRepository = expenseRepository;
        this.groupService = groupService;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public Balance findBalanceById(Long balanceId) {
        if(Objects.isNull(balanceId))
           throw new BalanceNotInformedException();

       Optional<Balance> balanceOptional =  balanceRepository.findById(balanceId);
       if(!balanceOptional.isPresent()){
           throw new BalanceNotFoundException();
       }
       return balanceOptional.get();
    }

    @Override
    public void checkBalanceOwnsGroup(Balance balance, Long groupId) {
        groupService.findGroupById(groupId);
        if(!balance.getGroup().getId().equals(groupId)){
           throw new BadRequestException();
        }
    }

    private BigDecimal totalSumExpensesAmount(List<TotalExpensePerUserDto> expenses){
        return expenses.stream()
                .map(TotalExpensePerUserDto::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getAmountPerUser(BigDecimal totalAmount, int totalUsers){
        return totalAmount.divide(BigDecimal.valueOf(totalUsers), RoundingMode.HALF_UP);
    }

    private List<TotalExpensePerUserDto> getTotalExpensesByUsers(Long groupId, Long balanceId){

        List<UserExpenseSummary> expensesPerUser = expenseRepository.findTotalAmountByUser(groupId, balanceId);

        return expensesPerUser.stream()
                .map(row -> new TotalExpensePerUserDto(row.getUserId(), row.getTotalAmount()))
             .collect(Collectors.toList());

    }
    private List<BalanceDto> getBalanceDto(List<User> users,
                                           List<TotalExpensePerUserDto> totalExpensesByUsers,
                                           BigDecimal amountPerUser){
        return users.stream()
                .map(user -> {
                    BalanceDto balanceDto = new BalanceDto();
                    balanceDto.setUserId(user.getId());
                    Optional<TotalExpensePerUserDto> totalExpensePerUserDto = totalExpensesByUsers
                            .stream()
                            .filter(totalUserExpense -> totalUserExpense.getUserId().equals(user.getId()))
                            .findFirst();
                    BigDecimal totalAmountPaid = BigDecimal.ZERO;
                    if(totalExpensePerUserDto.isPresent()){
                        totalAmountPaid = totalExpensePerUserDto.get().getTotalAmount();
                    }
                    balanceDto.setAmountPaid(totalAmountPaid);
                    balanceDto.setAmountFinal(totalAmountPaid.subtract(amountPerUser));
                    return balanceDto;
                })
                .collect(Collectors.toList());
    }

    private List<PaymentDto> getMinimumPayments(List<BalanceDto> balances){

        List<PaymentDto> paymentsDto = new ArrayList<>();

        List<BalanceDto> balanceBeneficiary = balances.stream()
                .filter(balance -> balance.getAmountFinal().compareTo(BigDecimal.ZERO) > 0)
                .sorted(Comparator.comparing(BalanceDto::getAmountFinal).reversed())
                .collect(Collectors.toList());


        List<BalanceDto> balanceDebtors = balances.stream()
                .filter(balance -> balance.getAmountFinal().compareTo(BigDecimal.ZERO) < 0)
                .sorted(Comparator.comparing(BalanceDto::getAmountFinal))
                .collect(Collectors.toList());


        Map<Long, BigDecimal> balancesAux = balances.stream()
                .collect(Collectors.toMap(
                        balance -> balance.getUserId(),
                        balance -> balance.getAmountFinal()));


        while (!balanceDebtors.isEmpty() && !balanceBeneficiary.isEmpty()) {

            BalanceDto debtor = balanceDebtors.get(0);
            BalanceDto creditor = balanceBeneficiary.get(0);

            BigDecimal debt = balancesAux.get(debtor.getUserId());
            BigDecimal credit = balancesAux.get(creditor.getUserId());

            BigDecimal payment = debt.abs().min(credit);

            balancesAux.put(debtor.getUserId(), debt.add(payment));
            balancesAux.put(creditor.getUserId(), credit.subtract(payment));

            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setBeneficiary(creditor.getUserId());
            paymentDto.setDebtor(debtor.getUserId());
            paymentDto.setAmountToPay(payment);

            paymentsDto.add(paymentDto);

            if (balancesAux.get(debtor.getUserId()).abs().compareTo(BigDecimal.valueOf(0.01)) < 0 )  {
                balanceDebtors.remove(0);
            }

            if (balancesAux.get(creditor.getUserId()).abs().compareTo(BigDecimal.valueOf(0.01)) < 0 )  {
                balanceBeneficiary.remove(0);
            }

        }

        return paymentsDto;

    }

    @Override
    @Transactional
    public BalanceSummaryDto getBalancesByGroup(Long groupId, Long balanceId) {

       Balance balance = findBalanceById(balanceId);
       checkBalanceOwnsGroup(balance, groupId);
       List<User> users =  balance.getGroup().getUsers();

       List<TotalExpensePerUserDto> totalExpensesByUsers = getTotalExpensesByUsers(groupId, balanceId);
       BigDecimal totalAmount =  totalSumExpensesAmount(totalExpensesByUsers);
       BigDecimal amountPerUser =  getAmountPerUser(totalAmount, users.size());
       List<BalanceDto> balances = getBalanceDto(users, totalExpensesByUsers, amountPerUser);

       BalanceSummaryDto balanceSummaryDto = new BalanceSummaryDto();
       balanceSummaryDto.setBalances(balances);
       balanceSummaryDto.setAmountPerUser(amountPerUser);
       balanceSummaryDto.setUsers(users.size());
       balanceSummaryDto.setTotalAmount(totalAmount);
       balanceSummaryDto.setPayments(getMinimumPayments(balances));

       return balanceSummaryDto;
    }
}
