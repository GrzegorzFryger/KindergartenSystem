package pl.edu.pja.prz.finances.service;

import pl.edu.pja.prz.finances.model.BalanceHistory;
import pl.edu.pja.prz.finances.model.dto.Balance;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BalanceService {
    Balance getBalance(UUID childId);

    void increaseBalance(UUID childId, BigDecimal amount, String title);

    void decreaseBalance(UUID childId, BigDecimal amount, String title);

    Balance calculateBalance(List<BalanceHistory> balanceHistories);
}
