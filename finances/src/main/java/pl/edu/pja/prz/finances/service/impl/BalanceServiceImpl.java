package pl.edu.pja.prz.finances.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.model.BalanceHistory;
import pl.edu.pja.prz.finances.model.dto.Balance;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.util.BigDecimalUtils.*;

@Service
public class BalanceServiceImpl implements BalanceService {
    private static final String BALANCE = "Balance";

    private final BalanceHistoryService historyService;

    @Autowired
    public BalanceServiceImpl(BalanceHistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public Balance getBalance(UUID childId) {
        List<BalanceHistory> result = historyService.getAllHistoryRecordsForChild(childId);
        if (result.isEmpty()) {
            throw new ElementNotFoundException(BALANCE, childId.toString());
        }
        return calculateBalance(result);
    }

    @Override
    public void increaseBalance(UUID childId, BigDecimal amount, String title) {
        if (isPositive(amount)) {
            historyService.saveBalanceInHistory(childId, amount, title);
        } else {
            throw new BusinessException("Attempt was made to increase balance when providing negative amount: " + amount);
        }
    }

    @Override
    public void decreaseBalance(UUID childId, BigDecimal amount, String title) {
        if (isNegative(amount)) {
            historyService.saveBalanceInHistory(childId, amount, title);
        } else {
            historyService.saveBalanceInHistory(childId, amount.negate(), title);
        }
    }

    @Override
    public Balance calculateBalance(List<BalanceHistory> balanceHistories) {
        BigDecimal receivables = BigDecimal.ZERO;
        BigDecimal liabilites = BigDecimal.ZERO;

        for (BalanceHistory balanceHistory : balanceHistories) {
            if (isNegative(balanceHistory.getAmountOfChange())) {
                liabilites = sum(liabilites, balanceHistory.getAmountOfChange());
            } else {
                receivables = sum(receivables, balanceHistory.getAmountOfChange());
            }
        }
        return new Balance(receivables, liabilites);
    }
}
