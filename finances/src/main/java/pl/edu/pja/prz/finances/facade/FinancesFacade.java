package pl.edu.pja.prz.finances.facade;

import pl.edu.pja.prz.finances.model.Balance;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface FinancesFacade {
	Balance getBalance(UUID childId);

	List<Balance> getBalances(UUID guardianId);

	Balance increaseBalance(UUID childId, BigDecimal amount);

	Balance decreaseBalance(UUID childId, BigDecimal amount);

	void saveBalanceInHistory(UUID childId, BigDecimal oldBalance, BigDecimal change);
}
