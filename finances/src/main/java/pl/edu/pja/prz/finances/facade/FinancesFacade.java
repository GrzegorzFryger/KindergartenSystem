package pl.edu.pja.prz.finances.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.finances.model.Balance;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class FinancesFacade {
    private final BalanceService balanceService;
    private final BalanceHistoryService balanceHistoryService;

    @Autowired
    public FinancesFacade(BalanceService balanceService, BalanceHistoryService balanceHistoryService) {
        this.balanceService = balanceService;
        this.balanceHistoryService = balanceHistoryService;
    }

    public Balance getBalance(UUID childId) {
        return balanceService.getBalance(childId);
    }

    public List<Balance> getBalances(UUID guardianId) {
        return balanceService.getBalances(guardianId);
    }

    public void increaseBalance(UUID childId, BigDecimal amount) {
        Balance beforeChange = getBalance(childId);
        balanceService.increaseBalance(childId, amount);
        saveBalanceInHistory(childId, beforeChange.getAmount(), amount);
    }

    public void decreaseBalance(UUID childId, BigDecimal amount) {
        Balance beforeChange = getBalance(childId);
        balanceService.decreaseBalance(childId, amount);
        saveBalanceInHistory(childId, beforeChange.getAmount(), amount);
    }

    public void saveBalanceInHistory(UUID childId, BigDecimal oldBalance, BigDecimal change) {
        balanceHistoryService.saveBalanceInHistory(childId, oldBalance, change);
    }
}
