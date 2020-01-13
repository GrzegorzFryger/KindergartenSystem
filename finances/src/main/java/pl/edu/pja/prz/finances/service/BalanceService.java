package pl.edu.pja.prz.finances.service;

import pl.edu.pja.prz.finances.model.Balance;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BalanceService {
    Balance getBalance(UUID childId);

    List<Balance> getBalances(UUID guardianId);

    Balance increaseBalance(UUID childId, BigDecimal amount);

    Balance decreaseBalance(UUID childId, BigDecimal amount);
}
