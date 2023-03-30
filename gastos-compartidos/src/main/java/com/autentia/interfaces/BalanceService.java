package com.autentia.interfaces;



import com.autentia.domain.BalanceSummaryDto;
import com.autentia.model.Balance;

import java.util.List;

public interface BalanceService {
    Balance findBalanceById(Long balanceId);

    void checkBalanceOwnsGroup(Balance balanceEntity, Long groupId);

    BalanceSummaryDto getBalancesByGroup(Long groupId, Long balanceId);
}
