package pl.edu.pja.prz.finances.facade;

import pl.edu.pja.prz.finances.model.dto.AccountNumberDto;
import pl.edu.pja.prz.finances.model.dto.Balance;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface FinancesFacade {
    Balance getBalance(UUID childId);

    List<Balance> getBalancesForAllChildren(UUID guardianId);

    Balance getBalanceForAllChildren(UUID guardianId);

    void increaseBalance(UUID childId, BigDecimal amount, String title);

    void decreaseBalance(UUID childId, BigDecimal amount, String title);

    void applyReceivablesBalanceCorrection(UUID childId, BigDecimal amount, String title);

    void applyLiabilitiesBalanceCorrection(UUID childId, BigDecimal amount, String title);

    AccountNumberDto getAccountNumber(UUID childId);
}
