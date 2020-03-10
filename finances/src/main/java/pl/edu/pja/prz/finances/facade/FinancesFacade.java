package pl.edu.pja.prz.finances.facade;

import pl.edu.pja.prz.finances.model.dto.Balance;

import java.math.BigDecimal;
import java.util.UUID;

public interface FinancesFacade {
    Balance getBalance(UUID childId);

    void increaseBalance(UUID childId, BigDecimal amount, String title);

    void decreaseBalance(UUID childId, BigDecimal amount, String title);
}
