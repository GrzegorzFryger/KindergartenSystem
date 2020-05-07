package pl.edu.pja.prz.finances.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.finances.model.BalanceHistory;
import pl.edu.pja.prz.finances.model.dto.Balance;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.util.BigDecimalUtils.*;
import static pl.edu.pja.prz.finances.model.enums.OperationType.*;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final BalanceHistoryService historyService;

    @Autowired
    public BalanceServiceImpl(BalanceHistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public Balance getBalance(UUID childId) {
        List<BalanceHistory> result = historyService.getAllHistoryRecordsForChild(childId);
        Balance balance = calculateBalance(result);
        balance.setChildId(childId);
        return balance;
    }

    @Override
    public Balance getBalanceForAllChildren(List<UUID> childIdList, UUID guardianId) {
        List<BalanceHistory> result = new ArrayList<>();
        for (UUID id : childIdList) {
            result.addAll(historyService.getAllHistoryRecordsForChild(id));
        }
        Balance balance = calculateBalance(result);
        balance.setGuardianId(guardianId);
        return balance;
    }

    @Override
    public void increaseBalance(UUID childId, BigDecimal amount, String title) {
        if (childId != null) {
            if (isPositive(amount)) {
                historyService.saveBalanceInHistory(childId, amount, title, INCREASE);
            } else {
                throw new BusinessException("Attempt was made to increase balance when providing negative amount: " + amount);
            }
        }
    }

    @Override
    public void decreaseBalance(UUID childId, BigDecimal amount, String title) {
        if (childId != null) {
            if (isNegative(amount)) {
                historyService.saveBalanceInHistory(childId, amount, title, DECREASE);
            } else {
                historyService.saveBalanceInHistory(childId, amount.negate(), title, DECREASE);
            }
        }
    }

    @Override
    public void applyBalanceCorrection(UUID childId, BigDecimal amount, String title) {
        if (childId != null) {
            historyService.saveBalanceInHistory(childId, amount, title, CORRECTION);
        }
    }

    @Override
    public Balance calculateBalance(List<BalanceHistory> balanceHistories) {
        BigDecimal receivables = BigDecimal.ZERO;
        BigDecimal liabilites = BigDecimal.ZERO;

        for (BalanceHistory balanceHistory : balanceHistories) {
            if (CORRECTION.equals(balanceHistory.getOperationType())) {
                // For corrections we must reverse operation
                // For instance - when removing cash payment - amount is negative
                // so you don't add it to liabilities, instead you subtract receivables
                if (isNegative(balanceHistory.getAmountOfChange())) {
                    receivables = sum(receivables, balanceHistory.getAmountOfChange());
                } else {
                    liabilites = sum(liabilites, balanceHistory.getAmountOfChange());
                }
            } else {
                if (isNegative(balanceHistory.getAmountOfChange())) {
                    liabilites = sum(liabilites, balanceHistory.getAmountOfChange());
                } else {
                    receivables = sum(receivables, balanceHistory.getAmountOfChange());
                }
            }
        }
        return new Balance(receivables, liabilites);
    }
}
